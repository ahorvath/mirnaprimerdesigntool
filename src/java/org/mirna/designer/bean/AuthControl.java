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
@Table(name="authcontrol")
public class AuthControl implements Serializable,Comparable<AuthControl> {
    
    public static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)    
    @Column(name="id")    
    private Long id;
    
    @Column(name="suma")
    private Long suma = new Long(1);
    
    @Column(name="goal")
    private Long goal = new Long(0);

    @Column(name="faild")
    private Long faild = new Long(0);    
    
    @Column(name="ip")
    private String ip;
    
    @Column(name="hozt")
    private String hozt;
    
    @Column(name="lazt")
    private Timestamp lazt;
    
    public AuthControl() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSuma() {
        return (this.goal.longValue() + this.faild.longValue());
    }

    public void setSuma(Long suma) {
        this.suma = suma;
    }

    public Long getGoal() {
        return goal;
    }

    public void setGoal(Long goal) {
        this.goal = goal;
    }
    
    public Long getFaild() {
        return faild;
    }

    public void setFaild(Long faild) {
        this.faild = faild;
    }    

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHozt() {
        return hozt;
    }

    public void setHozt(String hozt) {
        this.hozt = hozt;
    }

    public Timestamp getLazt() {
        return lazt;
    }

    public void setLazt(Timestamp lazt) {
        this.lazt = lazt;
    }

    @Override
    public int compareTo(AuthControl o) {
        return -1 * (this.goal.compareTo( o.getGoal() ));
    }

}
