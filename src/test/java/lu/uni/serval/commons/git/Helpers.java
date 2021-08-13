package lu.uni.serval.commons.git;

/*-
 * #%L
 * GIT Utils
 * %%
 * Copyright (C) 2020 - 2021 University of Luxembourg, Renaud RWEMALIKA
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lu.uni.serval.commons.git.api.MapperFactory;
import lu.uni.serval.commons.git.utils.GitUtils;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.jgit.api.Git;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.fail;


public class Helpers {
    public static File unzip(File fileZip) throws IOException {
        final File destDir = new File(new File(System.getProperty("java.io.tmpdir")), "test-git-loader");
        destDir.mkdirs();

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);

            if(zipEntry.isDirectory()){
                newFile.mkdirs();
            }
            else{
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }

            zipEntry = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();

        return new File(destDir, FilenameUtils.getBaseName(fileZip.getAbsolutePath()));
    }

    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

    public static File getResourceFile(String name) throws IOException, URISyntaxException {
        URL resource = Helpers.class.getClassLoader().getResource(name);
        if (resource == null) {
            throw new IOException("Failed to locate resource template for project analytics");
        }

        return Paths.get(resource.toURI()).toFile();
    }

    public static String getResourceContent(String name) throws IOException, URISyntaxException {
        final StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(getResourceFile(name).toPath(), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }

        return contentBuilder.toString();
    }

    public static Git setRepository(String zipResource){
        try {
            File folder = Helpers.unzip(Helpers.getResourceFile(zipResource));
            return Git.open(new File(folder, ".git"));
        } catch (IOException | URISyntaxException e) {
            fail(String.format("Failed to load git repository from archive [%s]: %s", zipResource, e.getMessage()));
        }

        return null;
    }

    public static void deleteRepository(Git git){

        try {
            GitUtils.close(git, true);
        } catch (IOException e) {
            fail(String.format("Failed to delete working git repository [%s]: %s",
                    git.getRepository().getDirectory().getAbsolutePath(), e.getMessage()));
        }
    }

    public static <T> T deserializeJsonFromResources(String name, Class<T> type) throws IOException, URISyntaxException {
        final String json = getResourceContent(name);
        final ObjectMapper mapper = MapperFactory.create();
        final JavaType javaType = mapper.getTypeFactory().constructType(type);

        return mapper.readValue(json, javaType);
    }
}
