package org.example.game.models.model;
import org.example.game.brainBase.BrainBase;
import org.example.game.brainBase.BrainBaseExtensions;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Rating extends TSingleton<Rating>{
    private static final String DOC_NAME = "stats.data.xml";
    private Document document;
    private File statsFile;

    private Rating() {
        statsFile = new File(DOC_NAME);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            if (!statsFile.exists()) {
                document = builder.newDocument();
                Element root = document.createElement("Stats");
                document.appendChild(root);
                saveChanges();
            } else {
                document = builder.parse(statsFile);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Rating system", e);
        }
    }

    public void dispatchWinGameResult(BrainBase winner, BrainBase looser) {
        try {
            Element winnerElem = findPlayer(winner);
            Element looserElem = findPlayer(looser);

            increaseValue(winnerElem, "Wins");
            increaseValue(looserElem, "Loses");
            saveChanges();
        } catch (Exception e) {
            System.err.println("Error in dispatchWinGameResult: " + e.getMessage());
        }
    }

    public void dispatchDrawGameResult(BrainBase white, BrainBase black) {
        try {
            Element whiteElem = findPlayer(white);
            Element blackElem = findPlayer(black);

            increaseValue(whiteElem, "Draws");
            increaseValue(blackElem, "Draws");
            saveChanges();
        } catch (Exception e) {
            System.err.println("Error in dispatchDrawGameResult: " + e.getMessage());
        }
    }

    private void increaseValue(Element elem, String attrName) {
        String currentValue = elem.getAttribute(attrName);
        int value = currentValue.isEmpty() ? 0 : Integer.parseInt(currentValue);
        value++;
        elem.setAttribute(attrName, String.valueOf(value));
    }

    public BrainStats getBrainStats(BrainBase brain) {
        try {
            Element elem = findPlayer(brain);

            int wins = Integer.parseInt(elem.getAttribute("Wins"));
            int loses = Integer.parseInt(elem.getAttribute("Loses"));
            int draws = Integer.parseInt(elem.getAttribute("Draws"));

            return new BrainStats(wins, loses, draws);
        } catch (Exception e) {
            System.err.println("Error getting brain stats: " + e.getMessage());
            return new BrainStats();
        }
    }

    private Element findPlayer(BrainBase brain) {
        String name = BrainBaseExtensions.getName(brain);

        Element root = document.getDocumentElement();
        NodeList players = root.getElementsByTagName("Player");

        // Ищем существующего игрока
        for (int i = 0; i < players.getLength(); i++) {
            Element player = (Element) players.item(i);
            if (name.equals(player.getAttribute("Name"))) {
                return player;
            }
        }

        // Создаем нового игрока
        Element newPlayer = document.createElement("Player");
        newPlayer.setAttribute("Name", name);
        newPlayer.setAttribute("Wins", "0");
        newPlayer.setAttribute("Loses", "0");
        newPlayer.setAttribute("Draws", "0");

        root.appendChild(newPlayer);
        return newPlayer;
    }

    public void saveChanges() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(statsFile);
            transformer.transform(source, result);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save stats", e);
        }
    }

    // Метод для получения всех статистик (дополнительно)
    public List<BrainStats> getAllStats() {
        List<BrainStats> statsList = new ArrayList<>();
        Element root = document.getDocumentElement();
        NodeList players = root.getElementsByTagName("Player");

        for (int i = 0; i < players.getLength(); i++) {
            Element player = (Element) players.item(i);
            int wins = Integer.parseInt(player.getAttribute("Wins"));
            int loses = Integer.parseInt(player.getAttribute("Loses"));
            int draws = Integer.parseInt(player.getAttribute("Draws"));
            statsList.add(new BrainStats(wins, loses, draws));
        }

        return statsList;
    }
}
