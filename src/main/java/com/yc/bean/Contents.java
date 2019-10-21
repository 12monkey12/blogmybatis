package com.yc.bean;

import java.io.Serializable;

public class Contents implements Serializable{
	private static final long serialVersionUID = 6008952124941672642L;
	
	private int cid;
	private int tid;
	private int uid;
	private String title;
	private String addtime;
	private String desc;
	private String content;
	private int views;
	private String tname;
	private String uname;
	
	@Override
	public String toString() {
		return "Contents [cid=" + cid + ", tid=" + tid + ", uid=" + uid + ", title=" + title + ", addtime=" + addtime
				+ ", desc=" + desc + ", content=" + content + ", views=" + views + ", tname=" + tname + ", uname="
				+ uname + "]";
	}
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Contents(int cid, int tid, int uid, String title, String addtime, String desc, String content, int views,
			String tname, String uname) {
		super();
		this.cid = cid;
		this.tid = tid;
		this.uid = uid;
		this.title = title;
		this.addtime = addtime;
		this.desc = desc;
		this.content = content;
		this.views = views;
		this.tname = tname;
		this.uname = uname;
	}
	public Contents() {
		super();
	}
	
}
