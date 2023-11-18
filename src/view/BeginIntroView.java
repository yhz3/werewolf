package view;

import interface_adapter.begin_intro.BeginIntroController;
import interface_adapter.begin_intro.BeginIntroViewModel;
import use_case.begin_intro.BeginIntroInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public class BeginIntroView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "intro";
    private final BeginIntroViewModel beginIntroViewModel;
    private final BeginIntroController BeginIntroController;
    private final JButton beginIntro;

    public BeginIntroView(BeginIntroViewModel beginIntroViewModel, interface_adapter.begin_intro.BeginIntroController beginIntroController) {
        this.beginIntroViewModel = beginIntroViewModel;
        this.BeginIntroController = beginIntroController;
        beginIntroViewModel.addPropertyChangeListener(this);

        JPanel buttons = new JPanel();
        beginIntro = new JButton(BeginIntroViewModel.BUTTON_LABEL);
        buttons.add(beginIntro);

        beginIntro.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(beginIntro)) {
                            beginIntroController.execute(new BeginIntroInputData());
                        }
                    }
                }
        );

        JLabel title = new JLabel(BeginIntroViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel story = new JLabel();
        story.setText(beginIntroViewModel.getIntroStory());
        story.setBounds(0, 0, 200, 50);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(story);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof String state) {
            JTextArea textArea = new JTextArea(15, 30); // Set rows and columns to control size
            textArea.setText(state);
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setCaretPosition(0);
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(this, scrollPane);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
