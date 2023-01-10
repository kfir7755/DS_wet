public class Player_and_goals {
    private Player player;
    private int goals;

    public Player_and_goals(Player player, int goals) {
        this.player = player;
        this.goals = goals;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }
}
