import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class Processes {
	ArrayList<String> Permutations(String input, int r) {
		Set<String> permutationsSet = new HashSet<>();
		generatePermutations("", input, r, permutationsSet);
		return new ArrayList<>(permutationsSet);
	}

	private void generatePermutations(String prefix, String remaining, int r, Set<String> permutationsSet) {
		if (r == 0) {
			permutationsSet.add(prefix);
			return;
		}
		for (int i = 0; i < remaining.length(); i++) {
			String newPrefix = prefix + remaining.charAt(i);
			String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
			generatePermutations(newPrefix, newRemaining, r - 1, permutationsSet);
		}
	}

	ArrayList<String> Combinations(String input, int r) {
		ArrayList<String> combinationsList = new ArrayList<>();
		String combi;
		for (int i = 0; i < input.length(); i++) {
			for (int j = i + 1; j < input.length() + 1; j++) {
				combi = input.substring(i, j);
				combinationsList.add(combi);
			}
		}
		for (int i = 0; i < input.length(); i++) {
			for (int j = i + 2; j < input.length(); j++) {
				combi = "" + input.charAt(i) + input.charAt(j);
				combinationsList.add(combi);
			}
		}
		for (int i = 0; i < combinationsList.size(); i++) {
			if (combinationsList.get(i).length() != r) {
				combinationsList.remove(i);
				i--;
			}
		}
		Collections.sort(combinationsList);
		return combinationsList;
	}

	void typingText(String text, JTextArea textArea, JButton submitButton) {
		textArea.setText("");
		final int delay = 10;
		Timer timer = new Timer(delay, null);
		int[] index = { 0 };
		submitButton.setEnabled(false);
		timer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (index[0] < text.length()) {
					textArea.append(String.valueOf(text.charAt(index[0])));
					index[0]++;

				} else {
					submitButton.setEnabled(true);
					timer.stop();
				}
			}
		});

		timer.start();
	}

}
