package lu.uni.serval.commons.git.utils;

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

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Difference {
    private final Git git;
    private final List<DiffEntry> entries;
    private String formatted;

    public Difference(Git git, List<DiffEntry> entries) {
        this.git = git;
        this.entries = entries;
        this.formatted = null;
    }

    public static Difference none() {
        return new Difference(null, Collections.emptyList());
    }

    public List<DiffEntry> getEntries() {
        return entries;
    }

    public String getFormatted() throws IOException {
        if(git == null){
            formatted = "";
        }

        if(formatted == null){
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            DiffFormatter formatter = new DiffFormatter(outputStream);
            formatter.setRepository(git.getRepository());
            formatter.format(this.entries);

            this.formatted = outputStream.toString().trim();
        }

        return formatted;
    }
}
