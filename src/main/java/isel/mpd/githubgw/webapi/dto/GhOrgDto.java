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

package isel.mpd.githubgw.webapi.dto;

/**
 * Created by Miguel Gamboa on 05-06-2015.
 */
public class GhOrgDto {

    public int id;
    public String login;
    public String name;
    public String location;

    public GhOrgDto() {}

    public GhOrgDto(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "GhOrgDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
