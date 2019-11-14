import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;

import javax.swing.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStreamWriter;

public class TestForm {

    private JLabel lblUrl;
    private JPanel panelRoot;
    private JTextField txtUrl;
    private JComboBox selectRequestType;
    private JButton btnSubmit;
    private JTabbedPane tabPaneResponse;
    private JPanel tabPretty;
    private JPanel tabRaw;
    private JPanel tabHeader;
    private JFXPanel jfxPanel;

    /** frame **/
    JFrame jFrame;

    /** java fx **/
    WebView webComponent;

    public TestForm() {
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        String url = txtUrl.getText();
                        if ( url != null && url.length() > 0){
                            webComponent.getEngine().load(url);
                        }

                        System.out.println(webComponent.getEngine().executeScript("document.documentElement.outerHTML").toString());
                    }
                });
            }
        });
    }

    public void initAndGo(){

        jFrame = new JFrame();

        jFrame.add(panelRoot);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(200, 200, 800, 400);
        jFrame.setVisible(true);

        loadJavaFXScene();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestForm().initAndGo();
            }
        });
    }

    private void loadJavaFXScene(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                BorderPane borderPane = new BorderPane();
                webComponent = new WebView();



                webComponent.getEngine().load("http://google.com/");



                borderPane.setCenter(webComponent);
                Scene scene = new Scene(borderPane,450,450);
                jfxPanel.setScene(scene);

            }
        });
    }
}
