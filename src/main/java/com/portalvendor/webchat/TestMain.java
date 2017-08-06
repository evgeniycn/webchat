package com.portalvendor.webchat;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Evgeniy on 06.08.2017.
 */
public class TestMain {


    public static void main(String[] args) throws IOException, InterruptedException, XMPPException, SmackException {

        String address = "10.196.60.31";
        int port = 5222;
        String name = "admin";
        String password = "bitnami";
        InetAddress serverAddress = Inet4Address.getByName(address);

        /*AbstractXMPPConnection conn = new XMPPTCPConnection(name, password, address);
        conn.connect();*/

        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                //.setUsernameAndPassword(name, password)
                .setXmppDomain(address)
                .setHostAddress(serverAddress)
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .setDebuggerEnabled(false)
                .build();

        AbstractXMPPConnection conn = new XMPPTCPConnection(config);
        conn.connect();
        conn.login(name, password);

        Roster roster = Roster.getInstanceFor(conn);



        if (!roster.isLoaded())
            roster.reloadAndWait();
        Collection<RosterEntry> entries = roster.getEntries();
        List<RosterEntry> roasterInfo = new ArrayList<RosterEntry>();
        for (RosterEntry entry : entries) {
            roasterInfo.add(entry);
            System.out.println("Here: " + entry.toString());
            System.out.println("User: " + entry.getUser());//get userinfo
            System.out.println("User Name:"+entry.getName());//get username
            System.out.println("User Status: "+entry.canSeeHisPresence());//get status of user
        }


        ChatManager chatManager = ChatManager.getInstanceFor(conn);


        Thread.sleep(999999999);


    }



}
