/*
* The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection. The usergets the best result by two different Tm calculating methods.  The tool designs the miRNA specific sequence of the stem-loop RT primer as well. 
*
* copyright (C) 2009-2012 Astrid Research Ltd. 
* copyright (C) November, 2012 University of Debrecen, Clinical Genomic Center, Medical and Health Science Center, Debrecen, Hungary
*
* The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection.  The usergets the best result by two different Tm calculating methods.  The tool designs the miRNA specific sequence of the stem-loop RT primer as well. 
*
*    miRNA Design Tool is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    miRNA Design Tool is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with miRNA Primer Design Tool.  If not, see <http://www.gnu.org/licenses/>.
*/
/*
 * miRNA Design Tool - user object
 */

package org.mirna.designer.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Attila
 */
@Entity
@Table(name="userz")
public class Userz implements Serializable, Comparable<Userz>{
    
    public static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)    
    @Column(name="id")    
    private Long id;
    
    @Column(name="usname")
    private String usname;
    
    @Column(name="passMD5")
    private String passMD5;
    
    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;    
    
    @Column(name="comp_org")
    private String comp_org;    
    
    @Column(name="country")
    private String country;
    
    @Column(name="tel")
    private String tel;
    
    @Column(name="email")
    private String email;    
    
    @Column(name="usertype")
    private String usertype;    
    
    @Column(name="adminrol")
    private Boolean adminrol;
    
    @Column(name="valid")
    private Boolean valid;
    
    private transient String ip;
    
    private transient String ondate;
    
    private transient String sessid;
    
    public Userz() {}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    /*personal ->*/ 
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lasttname) {
        this.lastname = lasttname;
    }

    public String getComp_org() {
        return comp_org;
    }

    public void setComp_org(String comp_org) {
        this.comp_org = comp_org;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    
    /*<- personal*/
    
    /*identification ->*/    
    public String getUsname() {
        return usname;
    }

    public void setUsname(String usname) {
        this.usname = usname;
    }
    
    public String getPassMD5() {
        return passMD5;
    }

    public void setPassMD5(String passMD5) {
        this.passMD5 = passMD5;
    }    
    
    public Boolean getAdminrol() {
        return adminrol;
    }

    public void setAdminrol(Boolean adminrol) {
        this.adminrol = adminrol;
    }
    
    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }     

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
    
    public String getOndate() {
        return ondate;
    }

    public void setOndate(String ondate) {
        this.ondate = ondate;
    }    
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }    
    
    public String getSessid() {
        return sessid;
    }

    public void setSessid(String sessid) {
        this.sessid = sessid;
    }

    @Override
    public int compareTo(Userz o) {
        return this.usname.compareTo( o.getUsname() );
    }
    /*<- identification*/

    @Override
    public String toString() {

        return "Usrname: " + getUsname() +
               "\nFirst name: " + getFirstname() +
               "\nLast name: " + getLastname() +
               "\nE-mail: " + getEmail() +
               "\nOrganization: " + getComp_org() +
               "\nCountry: " + getCountry() 
               //"\nTel: " + getTel()  Tel not used
               ;

    }
    
}
