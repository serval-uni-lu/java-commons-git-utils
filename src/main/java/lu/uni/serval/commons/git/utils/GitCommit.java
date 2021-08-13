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


import java.time.Instant;

public class GitCommit {
    private static final GitCommit none = new GitCommit("", Instant.EPOCH);

    private final String id;
    private final String tag;
    private final Instant date;
    private Difference difference;

    public GitCommit(String id, Instant date) {
        this.id = id;
        this.tag = "";
        this.date = date;
        this.difference = Difference.none();
    }

    public GitCommit(String id, String tag, Instant date){
        this.id = id;
        this.tag = tag;
        this.date = date;
        this.difference = Difference.none();
    }

    public static GitCommit none(){
        return none;
    }

    public String getId() {
        return id;
    }

    public String getTag(){
        return tag;
    }

    public Instant getDate() {
        return date;
    }

    public void setDifference(Difference difference){
        this.difference = difference;
    }

    public Difference getDifference() {
        return difference;
    }

    public boolean isValid(){
        return !getId().isEmpty();
    }

    public boolean hasTag(){
        return !getTag().isEmpty();
    }
}
