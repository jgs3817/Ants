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

/*
Add the check for the case when button is removed, remove that from antData too
*/

public class VideoPanel extends JLayeredPane {
    private static ArrayList<ArrayList<Integer>> antData = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> individualAntData;
    private int buttonID;
    private static int[] frameID;
    private FBData dataFB;
    private LandingData landingData;
    private Drawing indicator;
    private static boolean drawFlag;

    public VideoPanel(){
        //System.out.println("Video panel constructor called");
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(455,251));
        //setLayout(new BorderLayout());
        indicator = new Drawing();

        frameID = new int[2];
        frameID[0] = 1;
        frameID[1] = 1;

        ArrayList<Integer> init = new ArrayList<Integer>();
        init.add(0);
        antData.add(init);

        BufferedImage initialImage = initialImage();
        /*
        BufferedImage overlayImage = initialOverlayImage();
        Graphics2D g = initialImage.createGraphics();

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g.drawImage(initialImage, 0, 0, this);

        g.drawImage(overlayImage, 0, 0, this);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.99f));
        */
        ImageIcon scaledImage = new ImageIcon(initialImage.getScaledInstance(494,271,Image.SCALE_DEFAULT)); //fixed scaling

        JLabel picLabel = new JLabel(scaledImage);
        picLabel.setBounds(new Rectangle(new Point(0,0), picLabel.getPreferredSize()));
        add(picLabel,JLayeredPane.DEFAULT_LAYER);

        //indicator.setPreferredSize(new Dimension(getWidth(),getHeight()));
        indicator.setBounds(new Rectangle(new Point(1,0), indicator.getPreferredSize()));
        add(indicator,JLayeredPane.PALETTE_LAYER);

        revalidate();
        repaint();


        /*
        if(FBPanel.getFrameID()>0) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    removeAll();
                    loadFrame();
                }
            }, 500, 500);
        }*/

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean flag;
                int index=0;

                int x_coordinate;
                int y_coordinate;
                x_coordinate = e.getPoint().x;
                y_coordinate = e.getPoint().y;

                indicator.removeAll();
                indicator.addCircle(new Point(x_coordinate-5,y_coordinate-5),new Color(0x3c993d),10);
                drawFlag = true;

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
        //antData.remove(0);
        //antData.remove(0);
        return antData;
    }

    public void fillIndividualAntData(ArrayList<Integer> individualAntData, int buttonID, int x_coordinate, int y_coordinate){
        individualAntData.add(buttonID);
        individualAntData.add(x_coordinate);
        individualAntData.add(y_coordinate);
    }

    public void loadFrame(){
        //System.out.println("loadFrame() called");
        removeAll();

        indicator = new Drawing();

        BufferedImage inputImage = convertImageByte();
        BufferedImage inputImage2 = convertFBImageByte();
        //System.out.println(inputImage);
        //System.out.println(inputImage2);
        BufferedImage overlay = new BufferedImage(inputImage.getWidth(),inputImage.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = overlay.createGraphics();


        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g.drawImage(inputImage2, 0, 0, this);

        g.drawImage(inputImage, 0, 0, this);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.99f));

        //System.out.println("Width: " + getWidth());
        //System.out.println("Height: " + getHeight());
        ImageIcon scaledImage = new ImageIcon(overlay.getScaledInstance(getWidth(),getHeight(),Image.SCALE_DEFAULT));

        JLabel picLabel = new JLabel(scaledImage);
        picLabel.setBounds(new Rectangle(new Point(0,0), picLabel.getPreferredSize()));
        add(picLabel,JLayeredPane.DEFAULT_LAYER);

        indicator.setBounds(new Rectangle(new Point(1,0), indicator.getPreferredSize()));
        add(indicator,JLayeredPane.PALETTE_LAYER);

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
        ByteArrayInputStream bis = new ByteArrayInputStream(dataFB.getOverlayImageByte());
        try {
            bImage = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
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

    private BufferedImage initialOverlayImage(){
        BufferedImage bImage = null;
        landingData = TalkServlet.getLandingData();
        if(landingData.getOverlayImageByte()!=null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(landingData.getOverlayImageByte());
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bImage;
    }
}