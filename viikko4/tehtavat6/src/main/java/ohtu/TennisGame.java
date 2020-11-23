package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }

    public String getSituation(int score) {
        String situation = "";
        switch (score) {
            case 0:
                situation += "Love";
                break;
            case 1:
                situation += "Fifteen";
                break;
            case 2:
                situation += "Thirty";
                break;
            case 3:
                situation += "Forty";
                break;
        }
        return situation;
    }

    public String scoresAreEven(int score) {
        String scoreString = "";
        if (score < 4) {
            scoreString = getSituation(score) + "-All";
        } else {
            scoreString = "Deuce";
        }
        return scoreString;
    }

    public String advantageOrWin() {
        String scoreString = "";
        int scoreDifference = player1Score - player2Score;
        if (scoreDifference == 1) {
            scoreString = "Advantage player1";
        } else if (scoreDifference == -1) {
            scoreString = "Advantage player2";
        } else if (scoreDifference >= 2) {
            scoreString = "Win for player1";
        } else {
            scoreString = "Win for player2";
        }
        return scoreString;
    }

    public String scoresAreNotEven() {
        String scoreString = "";
        scoreString += getSituation(player1Score);
        scoreString += "-";
        scoreString += getSituation(player2Score);
        return scoreString;
    }

    public String getScore() {
        String score = "";
        if (player1Score == player2Score) {            
            score += scoresAreEven(player1Score);
        } else if (player1Score >= 4 || player2Score >= 4) {            
            score += advantageOrWin();
        } else {           
            score += scoresAreNotEven();
        }
        return score;
    }
}
