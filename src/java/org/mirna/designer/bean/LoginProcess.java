/*
 * miRNA Design Tool - authentication
 */

package org.mirna.designer.bean;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name="loginprocess")
public class LoginProcess implements Serializable, Comparable<LoginProcess> {
    
    public static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)    
    @Column(name="id")
    private Long id;
    
    @Column(name="sessionID")
    private String sessionID;
    
    @Column(name="msg0")
    private String msg0;
    
    @Column(name="msg1")
    private String msg1;
    
    @Column(name="ts")
    private Timestamp timestamp;
    
    public LoginProcess() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getMsg0() {
        return msg0;
    }

    public void setMsg0(String msg0) {
        this.msg0 = msg0;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(LoginProcess o) {
        return -(this.timestamp.compareTo( o.getTimestamp() ));
    }

}
