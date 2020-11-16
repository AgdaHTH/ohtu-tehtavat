
package ohtu;

/*
{
name: "Travis Zajac",
nationality: "CAN",
assists: 16,
goals: 9,
penalties: 28,
team: "NJD",
games: 69
},
*/

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private int assists;
    private int goals;
    private int penalties; //onko tämä aina int?
    private String team;
    private int games;

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public String getNationality() {
        return nationality;
    }

    public int getAssists() {
        return assists;
    }

    public int getGoals() {
        return goals;
    }

    public int getPenalties() {
        return penalties;
    }

    public String getTeam() {
        return team;
    }

    public int getGames() {
        return games;
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "\t" + team + " " + goals + " + " + assists + " = " + (goals+assists);
    }
    
    @Override
    public int compareTo(Player otherPlayer) {
        return (otherPlayer.getGoals()+otherPlayer.getAssists())-(goals+assists);
    }
      
}
