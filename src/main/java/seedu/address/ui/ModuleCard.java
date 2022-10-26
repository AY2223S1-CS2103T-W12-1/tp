package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.Module;


/**
 * A UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Module module;

    @FXML
    private HBox cardPane;
    @FXML
    private Label moduleCode;
    @FXML
    private Label moduleTitle;
    @FXML
    private Label id;
    @FXML
    private Label lectureDetails;
    @FXML
    private Label tutorialDetails;
    @FXML
    private Label lectureZoomLink;
    @FXML
    private Label tutorialZoomLink;
    @FXML
    private FlowPane assignmentDetails;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ModuleCard(Module module, int displayedIndex) {
        super(FXML);
        this.module = module;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(module.getModuleCode().moduleCode);
        moduleTitle.setText(module.getModuleCode().getModuleTitle());
        lectureDetails.setText(module.getLectureDetails().value);
        tutorialDetails.setText(module.getTutorialDetails().value);
        lectureZoomLink.setText(module.getLectureZoomLink().zoomLink);
        tutorialZoomLink.setText(module.getTutorialZoomLink().zoomLink);
        module.getAssignmentDetails().stream()
            .sorted(Comparator.comparing(assignment -> assignment.assignmentDetails))
            .forEach(assignment -> assignmentDetails.getChildren().add(new Label(assignment.assignmentDetails)));
    }

    @FXML
    private void copyLectureZoomUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(lectureZoomLink.getText());
        clipboard.setContent(url);
    }

    @FXML
    private void copyTutorialZoomUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(tutorialZoomLink.getText());
        clipboard.setContent(url);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCard)) {
            return false;
        }

        // state check
        ModuleCard card = (ModuleCard) other;
        return id.getText().equals(card.id.getText())
            && module.equals(card.module);
    }
}
