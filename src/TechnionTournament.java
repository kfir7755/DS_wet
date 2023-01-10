import java.util.ArrayList;

public class TechnionTournament implements Tournament{

    TechnionTournament(){};

    @Override
    public void init() {
        faculties_tree_by_fid = new two_three_tree<>(Integer.MAX_VALUE,Integer.MIN_VALUE);
        players_tree_by_pid = new two_three_tree<>(Integer.MAX_VALUE,Integer.MIN_VALUE);
        faculties_tree_by_points = new upgraded_two_three_tree<>(Pair.max(),Pair.min());
        players_tree_by_goals  = new upgraded_two_three_tree<>(Pair.max(),Pair.min());

        faculties_tree_by_fid.two_three_init();
        players_tree_by_pid.two_three_init();
        faculties_tree_by_points.two_three_init();
        players_tree_by_goals.two_three_init();
    }

    @Override
    public void addFacultyToTournament(Faculty faculty) {
        //insert a new node with the given faculty ID and 0 points
        faculties_tree_by_fid.insert(new Node<>(faculty.getId(),0));
        Pair pair = new Pair(0,faculty.getId(),faculty);
        //empty array list since there are no players in the team yet
        faculties_tree_by_points.insert(new Node<>(pair,new Player_and_goals[maxTeamSize]));
    }

    @Override
    public void removeFacultyFromTournament(int faculty_id){
		Node<Integer,Integer> node = faculties_tree_by_fid.two_three_search(faculties_tree_by_fid.getRoot(),faculty_id);
        Pair pair = new Pair(node.getValue(), node.getKey(),null);
        Node<Pair<Faculty>, Player_and_goals[]> node_to_delete =
                faculties_tree_by_points.two_three_search(faculties_tree_by_points.getRoot(),pair);
        faculties_tree_by_fid.delete(node);
        faculties_tree_by_points.delete(node_to_delete);
    }

    @Override
    public void addPlayerToFaculty(int faculty_id,Player player) {
        Player player_copy = new Player(player.getId(),player.getName());
        Player_and_goals pag = new Player_and_goals(player_copy,0);
        players_tree_by_pid.insert(new Node<Integer, Integer>(player_copy.getId(),0));
        Pair pair = new Pair (0,player_copy.getId(),player_copy);
        players_tree_by_goals.insert(new Node<>(pair,player_copy));
        Node<Integer,Integer> node = faculties_tree_by_fid.two_three_search(faculties_tree_by_fid.getRoot(),faculty_id);
        Pair points_and_fid = new Pair(node.getValue(),node.getKey(),player_copy);
        Node<Pair, Player_and_goals[]> faculty_node =
                faculties_tree_by_points.two_three_search(faculties_tree_by_points.getRoot(),points_and_fid);
        add_player_to_array(pag, faculty_node.getValue());
    }


    @Override
    public void removePlayerFromFaculty(int faculty_id, int player_id) {
        Node<Integer,Integer> node = faculties_tree_by_fid.two_three_search(faculties_tree_by_fid.getRoot(),faculty_id);
        Pair points_and_fid = new Pair(node.getValue(),node.getKey(),null);
        Node<Pair, Player_and_goals[]> faculty_node =
                faculties_tree_by_points.two_three_search(faculties_tree_by_points.getRoot(),points_and_fid);
        remove_player_to_array(player_id,faculty_node.getValue());
    }


    @Override
    public void playGame(int faculty_id1, int faculty_id2, int winner,
                         ArrayList<Integer> faculty1_goals, ArrayList<Integer> faculty2_goals) {
        Node<Integer,Integer> node1 = faculties_tree_by_fid.two_three_search(faculties_tree_by_fid.getRoot(),faculty_id1);
        Pair points_and_fid1 = new Pair(node1.getValue(),node1.getKey(),null);
        Node<Pair<Faculty>, Player_and_goals[]> faculty1_node =
                faculties_tree_by_points.two_three_search(faculties_tree_by_points.getRoot(),points_and_fid1);
        Node<Integer,Integer> node2 = faculties_tree_by_fid.two_three_search(faculties_tree_by_fid.getRoot(),faculty_id2);
        Pair points_and_fid2 = new Pair(node2.getValue(),node2.getKey(),null);
        Node<Pair<Faculty>, Player_and_goals[]> faculty2_node =
                faculties_tree_by_points.two_three_search(faculties_tree_by_points.getRoot(),points_and_fid2);
        for (int pid1 : faculty1_goals){
            Node<Integer,Integer> player1_node1 =
                    players_tree_by_pid.two_three_search(players_tree_by_pid.getRoot(),pid1);
            Pair<Player> player1_pair = new Pair(player1_node1.getValue(), player1_node1.getKey(),null);
            Node<Pair<Player>,Player> player1_node2 =
                    players_tree_by_goals.two_three_search(players_tree_by_goals.getRoot(),player1_pair);
            Player player1 = player1_node2.getValue();
            players_tree_by_goals.delete(player1_node2);
            player1_pair.setMainKey(player1_pair.getMainKey() + 1);
            player1_pair.setType(player1);
            players_tree_by_goals.insert(new Node<>(player1_pair,player1));
            players_tree_by_pid.delete(player1_node1);
            player1_node1.setValue(player1_node1.getValue() + 1);
            players_tree_by_pid.insert(player1_node1);
            add_goal_to_player(pid1,faculty1_node.getValue());
        }
        for (int pid2 : faculty2_goals){
            Node<Integer,Integer> player2_node1 =
                    players_tree_by_pid.two_three_search(players_tree_by_pid.getRoot(),pid2);
            Pair<Player> player2_pair = new Pair(player2_node1.getValue(), player2_node1.getKey(),null);
            Node<Pair<Player>,Player> player2_node2 =
                    players_tree_by_goals.two_three_search(players_tree_by_goals.getRoot(),player2_pair);
            Player player2 = player2_node2.getValue();
            players_tree_by_goals.delete(player2_node2);
            player2_pair.setMainKey(player2_pair.getMainKey() + 1);
            player2_pair.setType(player2);
            players_tree_by_goals.insert(new Node<>(player2_pair,player2));
            players_tree_by_pid.delete(player2_node1);
            player2_node1.setValue(player2_node1.getValue() + 1);
            players_tree_by_pid.insert(player2_node1);
            add_goal_to_player(pid2,faculty2_node.getValue());
        }
        if (winner == 1 || winner == 0){
            faculties_tree_by_points.delete(faculty1_node);
            faculties_tree_by_fid.delete(node1);
            if (winner == 1){
                faculty1_node.getKey().setMainKey(faculty1_node.getKey().getMainKey() + 3);
                node1.setValue(node1.getValue()+ 3);
            }
            else {
                faculty1_node.getKey().setMainKey(faculty1_node.getKey().getMainKey() + 1);
                node1.setValue(node1.getValue()+ 1);
            }
            faculties_tree_by_points.insert(faculty1_node);
            faculties_tree_by_fid.insert(node1);
        }
        if (winner == 2 || winner == 0){
            faculties_tree_by_points.delete(faculty2_node);
            faculties_tree_by_fid.delete(node2);
            if (winner == 2){
                faculty2_node.getKey().setMainKey(faculty2_node.getKey().getMainKey() + 3);
                node2.setValue(node2.getValue()+ 3);
            }
            else {
                faculty2_node.getKey().setMainKey(faculty2_node.getKey().getMainKey() + 1);
                node2.setValue(node2.getValue()+ 1);
            }
            faculties_tree_by_points.insert(faculty2_node);
            faculties_tree_by_fid.insert(node2);
        }
    }

    @Override
    public void getTopScorer(Player player) {
        Player top_goalscorer = players_tree_by_goals.Max().getValue();
        player.setId(top_goalscorer.getId());
        player.setName(top_goalscorer.getName());
    }


    @Override
    public void getTopScorerInFaculty(int faculty_id, Player player) {
        Node<Integer,Integer> node = faculties_tree_by_fid.two_three_search(faculties_tree_by_fid.getRoot(),faculty_id);
        Pair points_and_fid = new Pair(node.getValue(),node.getKey(),null);
        Node<Pair, Player_and_goals[]> faculty_node =
                faculties_tree_by_points.two_three_search(faculties_tree_by_points.getRoot(),points_and_fid);
        set_topscorer_from_array(faculty_node.getValue(),player);
    }

    @Override
    public void getTopKFaculties(ArrayList<Faculty> faculties, int k, boolean ascending) {
            Faculty[] faculties_arr= new Faculty[k];
            Node<Pair<Faculty>,Player_and_goals[]> node = faculties_tree_by_points.Max();
            for (int i=0;i<k;i++){
                faculties_arr[i]=node.getKey().getType();
                node=node.getPredecessor();
            }
            if (ascending){
                for (int i=k-1;i>=0;i--){
                    faculties.add(faculties_arr[i]);
                }
            }
            else{
                for (int i=0;i<k;i++){
                    faculties.add(faculties_arr[i]);
                }
            }
    }

    @Override
    public void getTopKScorers(ArrayList<Player> players, int k, boolean ascending) {
        Player[] players_arr= new Player[k];
        Node<Pair<Player>,Player> node = players_tree_by_goals.Max();
        for (int i=0;i<k;i++){
            players_arr[i]=node.getKey().getType();
            node=node.getPredecessor();
        }
        if (ascending){
            for (int i=k-1;i>=0;i--){
                players.add(players_arr[i]);
            }
        }
        else{
            for (int i=0;i<k;i++){
                players.add(players_arr[i]);
            }
        }
    }

    @Override
    public void getTheWinner(Faculty faculty) {
        Faculty winner = faculties_tree_by_points.Max().getKey().getType();
        faculty.setId(winner.getId());
        faculty.setName(winner.getName());
    }

    ///TODO - add below your own variables and methods

    private void add_player_to_array(Player_and_goals pag,Player_and_goals[] arr){
        int i=0;
        while (i<maxTeamSize-1 && arr[i] != null){
            i++;
        }
        arr[i]=pag;
    }

    private void set_topscorer_from_array(Player_and_goals[] arr,Player player_to_set){
        Player player_with_max_goals = arr[0].getPlayer();
        int max_goals = arr[0].getGoals();
        for (int i=1;i<maxTeamSize;i++){
            if(arr[i] !=null) {
                if (arr[i].getGoals() > max_goals ||
                        (arr[i].getGoals() == max_goals && arr[i].getPlayer().getId() < player_with_max_goals.getId())) {
                    player_with_max_goals = arr[i].getPlayer();
                    max_goals = arr[i].getGoals();
                }
            }
        }
        player_to_set.setName(player_with_max_goals.getName());
        player_to_set.setId(player_with_max_goals.getId());
    }


    private void add_goal_to_player(int pid,Player_and_goals[] arr){
        int i=0;
        while (i<maxTeamSize-1){
            if (arr[i] != null){
                if(arr[i].getPlayer().getId() == pid) break;
            }
            i++;
        }
        arr[i].setGoals(arr[i].getGoals() + 1);
    }


    private void remove_player_to_array(int pid,Player_and_goals[] arr){
        int i=0;
        while (i<maxTeamSize-1){
            if(arr[i] != null){
                if( arr[i].getPlayer().getId() == pid) break;
            }
            i++;
        }
        for(;i<maxTeamSize-1;i++) {
            arr[i] = arr[i + 1];
        }
        arr[maxTeamSize-1]=null;
    }

    static final int maxTeamSize =11;
    two_three_tree<Integer,Integer> faculties_tree_by_fid;
    two_three_tree<Integer,Integer> players_tree_by_pid;
    upgraded_two_three_tree<Pair<Faculty>,Player_and_goals[]> faculties_tree_by_points;
    upgraded_two_three_tree<Pair<Player>,Player> players_tree_by_goals;
}
