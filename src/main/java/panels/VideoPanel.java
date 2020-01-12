package panels;

import data_transfer.FBData;
import data_transfer.LandingData;
import data_transfer.TalkServlet;
import panels.ButtonPanel;
import shapes.Drawing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

/*
The panels.VideoPanel class is a JPanel that is used to display the frames of the
video. The panel also has a mouse listener to track the coordinates of the
ant, which is then stored in an ArrayList called antData
*/

public class VideoPanel extends JLayeredPane {
    private static ArrayList<ArrayList<Integer>> antData = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> individualAntData;
    private int buttonID;
    private FBData dataFB;
    private LandingData landingData;
    private Drawing indicator;                                  //JPanel Drawing allows visual feedback to be drawn
    private static boolean drawFlag;
    private static String initButton;
    private LandingData dataLanding;
    private Drawing initIndicator;

    public VideoPanel(){
        dataLanding = TalkServlet.getLandingData();
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(910,502));
        indicator = new Drawing();

        ArrayList<Integer> init = new ArrayList<Integer>();
        init.add(0);
        antData.add(init);

        BufferedImage initialImage = initialImage();
        ImageIcon scaledImage = new ImageIcon(initialImage.getScaledInstance(1070,598,Image.SCALE_DEFAULT)); //fixed scaling
        JLabel picLabel = new JLabel(scaledImage);
        picLabel.setBounds(new Rectangle(new Point(0,0), picLabel.getPreferredSize()));
        add(picLabel,2);

        indicator.setBounds(new Rectangle(new Point(1,0), indicator.getPreferredSize()));
        add(indicator,0);

        if(dataLanding.getAntData() != null) {
            for (ArrayList<Integer> i : dataLanding.getAntData()) {
                initButton = Integer.toString(i.get(0));
                Point p = new Point(i.get(1), i.get(2));
                indicator.addCircle(p, new Color(0x56DB57), 20, initButton);
            }
        }
        revalidate();
        repaint();

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean flag;
                int index=0;

                int x_coordinate;
                int y_coordinate;
                x_coordinate = e.getPoint().x;
                y_coordinate = e.getPoint().y;

                if(ButtonPanel.getLastButton()!=null) {
                    remove(indicator);
                    indicator = new Drawing();
                    indicator.setBounds(new Rectangle(new Point(1,0), indicator.getPreferredSize()));
                    add(indicator,0);
                    indicator.addCircle(new Point(x_coordinate - 5, y_coordinate - 5), new Color(0x56DB57), 20, ButtonPanel.getLastButton().getText());
                    drawFlag = true;
                }

                individualAntData = new ArrayList<Integer>();

                if(ButtonPanel.getLastButton()!=null) {
                    buttonID = Integer.parseInt(ButtonPanel.getLastButton().getText());
                    fillIndividualAntData(individualAntData, buttonID, x_coordinate, y_coordinate);

                    flag=false;
                    for(int i=0; i<antData.size(); i++){
                        if(individualAntData.get(0) == antData.get(i).get(0)) {
                            flag=true;
                            index=i;
                        }
                    }

                    if(flag){
                        antData.set(index, individualAntData);
                    }
                    else{
                        antData.add(individualAntData);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public static void setDrawFlag(boolean state){
        drawFlag = state;
    }

    public static boolean getDrawFlag(){
        return drawFlag;
    }

    public static ArrayList<ArrayList<Integer>> getAntData() {
        return antData;
    }

    private void fillIndividualAntData(ArrayList<Integer> individualAntData, int buttonID, int x_coordinate, int y_coordinate){
        individualAntData.add(buttonID);
        individualAntData.add(x_coordinate);
        individualAntData.add(y_coordinate);
    }

    public void loadFrame(){
        removeAll();

        dataFB = TalkServlet.getFBData();
        if(dataFB.getOverlayAntData() != null){
            initIndicator = new Drawing();
            initIndicator.setBounds(new Rectangle(new Point(2,0), initIndicator.getPreferredSize()));
            add(initIndicator, 1);
            for(ArrayList<Integer> i : dataFB.getOverlayAntData()){
                initButton = Integer.toString(i.get(0));
                Point p = new Point(i.get(1), i.get(2));
                initIndicator.addCircle(p, new Color(0xDB3835), 20, initButton+"'");
            }
        }

        indicator = new Drawing();

        BufferedImage inputImage = convertImageByte();
        BufferedImage inputImage2 = convertFBImageByte();

        BufferedImage overlay = new BufferedImage(inputImage.getWidth(),inputImage.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = overlay.createGraphics();

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g.drawImage(inputImage2, 0, 0, this);

        g.drawImage(inputImage, 0, 0, this);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.99f));

        ImageIcon scaledImage = new ImageIcon(overlay.getScaledInstance(getWidth(),getHeight(),Image.SCALE_DEFAULT));

        JLabel picLabel = new JLabel(scaledImage);
        picLabel.setBounds(new Rectangle(new Point(0,0), picLabel.getPreferredSize()));
        add(picLabel,2);

        indicator.setBounds(new Rectangle(new Point(1,0), indicator.getPreferredSize()));
        add(indicator,0);

        revalidate();
        repaint();
    }

    private BufferedImage convertImageByte(){
        BufferedImage bImage = null;
        dataFB = TalkServlet.getFBData();
        ByteArrayInputStream bis = new ByteArrayInputStream(dataFB.getImageByte());
        try {
            bImage = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bImage;
    }

    private BufferedImage convertFBImageByte(){
        BufferedImage bImage = null;
        dataFB = TalkServlet.getFBData();
        if(dataFB.getOverlayImageByte()!=null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(dataFB.getOverlayImageByte());
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bImage;
    }

    private BufferedImage initialImage(){
        BufferedImage bImage = null;
        landingData = TalkServlet.getLandingData();
        ByteArrayInputStream bis = new ByteArrayInputStream(landingData.getImageByte());
        try {
            bImage = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bImage;
    }
}