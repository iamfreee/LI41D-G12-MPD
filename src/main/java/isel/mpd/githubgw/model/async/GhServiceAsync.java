/*
 * Copyright (C) 2015 Miguel Gamboa at CCISEL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package isel.mpd.githubgw.model.async;

import isel.mpd.githubgw.model.IGhOrg;
import isel.mpd.githubgw.model.IGhRepo;
import isel.mpd.githubgw.model.IGhUser;
import isel.mpd.githubgw.model.streams.ContributorsLazyStream;
import isel.mpd.githubgw.webapi.GhApi;
import isel.mpd.githubgw.webapi.dto.GhOrgDto;
import isel.mpd.githubgw.webapi.dto.GhRepoDto;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Miguel Gamboa on 08-06-2015.
 */
public class GhServiceAsync implements AutoCloseable {

    public final GhApi gh;
    public HashMap<IGhUser, Set<IGhOrg>> identities;
    public Set<IGhOrg> orgs;

    public GhServiceAsync() {
        this(new GhApi());
    }

    public GhServiceAsync(GhApi ghApi) {
        this.gh = ghApi;
        this.orgs = new TreeSet<>((o, o1) -> o.getId() - o1.getId());
        this.identities = new HashMap<>();
    }

    public CompletableFuture<IGhOrg> getOrg(String login) throws ExecutionException, InterruptedException {
        return this.gh.getOrg(login).thenApplyAsync((GhOrgDto dto) -> {
            IGhOrg org = new GhOrg(dto, getRepos(dto.id));
            this.orgs.add(org);

            return org;
        });
    }

    public CompletableFuture<Stream<IGhRepo>> getRepos(int id) {
        CompletableFuture<List<GhRepoDto>> future = gh.getOrgRepos(id);
        return CompletableFuture.supplyAsync(() -> {
            Iterable<IGhRepo> i = new ContributorsLazyStream<>(this, id, future);
            return StreamSupport.stream((i.spliterator()), false);
        });
    }


    @Override
    public void close() throws Exception {
        if (!gh.isClosed())
            gh.close();
    }

    public boolean isClosed() {
        return gh.isClosed();
    }

    public CompletableFuture<Stream<IGhUser>> getRepoContributors(String login, String name, IGhOrg org) {
        return this.gh.getRepoContributors(login, name)
                .thenApply((list) -> list.stream().map((dto) -> {

                    IGhUser u = new GhUser(dto);
                    Set<IGhOrg> s = null;

                    if ((s = this.identities.get(u)) == null) {
                        s = new TreeSet<IGhOrg>((o, o1) -> o.getId() - o1.getId());
                        this.identities.put(u, s);
                    }
                    s.add(org);

                    return u;
                })).thenApply();
    }

}
