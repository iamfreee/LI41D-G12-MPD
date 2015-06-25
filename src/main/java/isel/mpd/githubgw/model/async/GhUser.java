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
import isel.mpd.githubgw.model.IGhUser;
import isel.mpd.githubgw.webapi.dto.GhUserDto;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Miguel Gamboa on 08-06-2015.
 */
public class GhUser implements IGhUser {

    private final int id;
    private final String login;
    private final String name;
    private final String company;
    private List<IGhOrg> orgs;

    public GhUser(GhUserDto dto) {
        this.id = dto.id;
        this.login = dto.login;
        this.name = dto.name;
        this.company = dto.company;
    }

    public void addOrg(IGhOrg o) {
        this.orgs.add(o);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getLogin() {
        return this.login;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCompany() {
        return this.company;
    }

    @Override
    public Stream<IGhOrg> getOrgs() {
        return this.orgs.stream();
    }
}
