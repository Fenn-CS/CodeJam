/**
 A small fire started in the senate room, and it needs to be evacuated!
There are some senators in the senate room, each of whom belongs to of one of N political parties. Those parties are named after the first N (uppercase) letters of the English alphabet.
The emergency door is wide enough for up to two senators, so in each step of the evacuation, you may choose to remove either one or two senators from the room.
The senate rules indicate the senators in the room may vote on any bill at any time, even in the middle of an evacuation! So, the senators must be evacuated in a way that ensures that no party ever has an absolute majority. That is, it can never be the case after any evacuation step that more than half of the senators in the senate room belong to the same party.
Can you construct an evacuation plan? The senate is counting on you! 
 */


/**
 Method 
 Try Random evacuation
 Carry out evacuation if valid.
 Repeat until hall is empty
 */
package senateevacuation;

import java.util.Random;
import java.util.*;
import java.io.*;

/**
 *
 * @author fenn
 */
public class SenateEvacuation {

    int Parties;
    int[] partyMembers;
    String[] partyNames = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R"};
    List<String> Evacuated = new ArrayList<>();

    public void evacuate() {
        Random rand = new Random();
        int[] evc_parties = new int[2];
        int evc_party;

        //Pick a random party that has members and try evacaution
        do {
            evc_parties[0] = rand.nextInt(Parties);
            evc_parties[1] = rand.nextInt(Parties);
            evc_party = rand.nextInt(Parties);

        } while (evc_parties[0] == evc_parties[1]);

        if (this.balanceChecked(2, null, evc_party)) {
            this.partyMembers[evc_party] -= 2;
            this.Evacuated.add(partyNames[evc_party] + partyNames[evc_party]);

        } else if (this.balanceChecked(0, evc_parties, 0)) {

            this.partyMembers[evc_parties[0]]--;
            this.partyMembers[evc_parties[1]]--;

            this.Evacuated.add(partyNames[evc_parties[0]] + partyNames[evc_parties[1]]);
        } else if (this.balanceChecked(1, null, evc_party)) {

            this.partyMembers[evc_party]--;
            this.Evacuated.add(partyNames[evc_party]);

        }

    }

    public boolean balanceChecked(int evacuation, int[] parties, int party) {
        boolean balanced = true;
        int[] temp_partyMembers = this.partyMembers.clone();
        if (parties == null) {
            temp_partyMembers[party] = this.partyMembers[party] - evacuation;

        } else {

            temp_partyMembers[parties[0]]--;
            temp_partyMembers[parties[1]]--;

        }

        for (int i = 0; i < this.Parties; i++) {

            if ((temp_partyMembers[i]) > this.sum(temp_partyMembers) / 2) {
                // System.out.println(temp_partyMembers[i]+" and "+this.sum(temp_partyMembers) / 2);
                balanced = false;
                break;

            }

        }

        return balanced;
    }

    public int sum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        return sum;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            FileInputStream is = new FileInputStream(new File(args[0]));
            System.setIn(is);

        } catch (Exception e) {

            System.out.println(e);
        }

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        if (t >= 1 && t <= 100) {
            for (int i = 1; i <= t; ++i) {
                int num_parties = in.nextInt();
                int[] party_members = new int[num_parties];
                for (int j = 0; j < num_parties; j++) {
                    party_members[j] = in.nextInt();

                }

                SenateEvacuation senate = new SenateEvacuation();
                senate.Parties = num_parties;
                senate.partyMembers = party_members.clone();

                while (senate.sum(senate.partyMembers) > 1) {
                    senate.evacuate();
                   
                }
                System.out.print("Case #" + i + ":");
                for (int k = 0; k < senate.Evacuated.size(); k++) {
                    System.out.print(senate.Evacuated.get(k) + " ");
                     
                }
                System.out.println("");
              

            }
        }
    }

}
