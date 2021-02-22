package lu.uni.serval.commons.git;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.jgit.api.Git;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
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
            final File directory = git.getRepository().getDirectory();
            git.getRepository().close();
            FileUtils.forceDelete(directory);
        } catch (IOException e) {
            fail(String.format("Failed to delete working git repository [%s]: %s",
                    git.getRepository().getDirectory().getAbsolutePath(), e.getMessage()));
        }
    }


}
