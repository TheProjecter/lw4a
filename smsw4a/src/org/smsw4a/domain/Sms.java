/**
 * 
 */
package org.smsw4a.domain;

import java.util.Date;

/**
 * @author andres
 *
 */
public class Sms {
	
	private Long id;
	private String address;
	private String body;
	private Date date;
	private boolean read;
	private boolean seen;
	private SmsType type;

	/**
	 * 
	 */
	public Sms() {
		
	}
	
	public enum SmsType{
		/**
		 * 
		 */
		INCOMING,
		/**
		 * 
		 */
		OUTGOING,
		/**
		 * 
		 */
		DRAFT,
		/**
		 * 
		 */
		OUTGOING_SENDING,
		/**
		 * 
		 */
		OUTGOING_FAILED,
		/**
		 * 
		 */
		OUTGOING_LATER,
		/**
		 * 
		 */
		UNKNOWN
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public SmsType getType() {
		return type;
	}

	public void setType(SmsType type) {
		this.type = type;
	}
	
	

}
