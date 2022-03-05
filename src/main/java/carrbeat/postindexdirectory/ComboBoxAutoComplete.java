package carrbeat.postindexdirectory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;

import java.text.Normalizer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class ComboBoxAutoComplete<T> {

    private ComboBox<T> locality;

    String filter = "";
    private ObservableList<T> originalItems;

    public ComboBoxAutoComplete(ComboBox<T> locality) {
        this.locality = locality;
        originalItems = FXCollections.observableArrayList(locality.getItems());
        locality.setTooltip(new Tooltip());
        locality.setOnKeyPressed(this::handleOnKeyPressed);
        locality.setOnHidden(this::handleOnHiding);
    }


    public void handleOnKeyPressed(KeyEvent e) {
        ObservableList<T> filteredList = FXCollections.observableArrayList();
        KeyCode code = e.getCode();

        if (code.isLetterKey()) {
            filter += e.getText();
        }
        if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
            filter = filter.substring(0, filter.length() - 1);
            locality.setItems(originalItems);
        }
        if (code == KeyCode.ESCAPE) {
            filter = "";
        }
        if (filter.length() == 0) {
            filteredList = originalItems;
            locality.getTooltip().hide();
        } else {
            Stream<T> items = locality.getItems().stream();
            String txtUsr = unaccent(filter.toLowerCase());
            items.filter(el -> unaccent(el.toString().toLowerCase()).contains(txtUsr)).forEach(filteredList::add);
            locality.getTooltip().setText(txtUsr);
            Window stage = locality.getScene().getWindow();
            double posX = stage.getX() + locality.getBoundsInParent().getMinX();
            double posY = stage.getY() + locality.getBoundsInParent().getMinY();
            locality.getTooltip().show(stage, posX, posY);
            locality.show();
        }
        locality.setItems(filteredList);
    }

    public void handleOnHiding(Event e) {
        filter = "";
        locality.getTooltip().hide();
        T s = locality.getSelectionModel().getSelectedItem();
        locality.setItems(originalItems);
        locality.getSelectionModel().select(s);
    }

    private String unaccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

}