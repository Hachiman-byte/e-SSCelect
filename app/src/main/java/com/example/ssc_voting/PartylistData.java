package com.example.ssc_voting;

public class PartylistData {

    private String partylistname;
    private String partylistlogo;

    public String getPartylistname() {
        return partylistname;
    }

    public String getPartylistlogo() {
        return partylistlogo;
    }

    public PartylistData(String partylistname, String partylistlogo) {
        this.partylistname = partylistname;
        this.partylistlogo = partylistlogo;
    }

    public PartylistData() {
    }
}
