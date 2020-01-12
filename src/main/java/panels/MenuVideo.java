package panels;

import data_transfer.InitData;
import data_transfer.TalkServlet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

/*
The panels.MenuVideo class is a JPanel which contains the menu of videos
*/

public class MenuVideo extends JPanel{
    private JButton vid1, vid2, vid3, vid4;
    static int vidID;
    private ArrayList<InitData> initData;
    BufferedImage bImage;

    public MenuVideo(){
        bImage = null;

        //stores 4 initData objects which are used to initialise the thumbnails and progress bars
        initData = new ArrayList<>();
        initData.add(TalkServlet.getInitData1());
        initData.add(TalkServlet.getInitData2());
        initData.add(TalkServlet.getInitData3());
        initData.add(TalkServlet.getInitData4());

        //Creating the 4 progress bars of each video
        ProgressBar prog1 = new ProgressBar(initData.get(0).getProgress().get(1),initData.get(0).getProgress().get(0));
        ProgressBar prog2 = new ProgressBar(initData.get(1).getProgress().get(1),initData.get(1).getProgress().get(0));
        ProgressBar prog3 = new ProgressBar(initData.get(2).getProgress().get(1),initData.get(2).getProgress().get(0));
        ProgressBar prog4 = new ProgressBar(initData.get(3).getProgress().get(1),initData.get(3).getProgress().get(0));

        //Creating the JButtons, which are thumbnails, of each video
        vid1 = setThumbnail(initData, 1);
        vid2 = setThumbnail(initData,2);
        vid3 = setThumbnail(initData,3);
        vid4 = setThumbnail(initData, 4);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor=GridBagConstraints.FIRST_LINE_START;

        setGridBagLayout(c,0,0,1,1);
        add(vid1,c);
        setGridBagLayout(c,0,1,1,1);
        add(prog1,c);

        setGridBagLayout(c,1,0,1,1);
        add(vid2,c);
        setGridBagLayout(c,1,1,1,1);
        add(prog2,c);


        setGridBagLayout(c,0,2,1,1);
        add(vid3,c);
        setGridBagLayout(c,0,3,1,1);
        add(prog3,c);

        setGridBagLayout(c,1,2,1,1);
        add(vid4,c);
        setGridBagLayout(c,1,3,1,1);
        add(prog4,c);

        vidID = 1;          //Video 1 selected by default

        setSize(200,200);

        vid1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vidID = 1;
            }
        });

        vid2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vidID = 2;
            }
        });

        vid3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vidID = 3;
            }
        });

        vid4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vidID = 4;
            }
        });
    }

    private void setGridBagLayout(GridBagConstraints c, int gx, int gy, int wx, int wy){
        c.gridx = gx;
        c.gridy = gy;
        c.weightx = wx;
        c.weighty = wy;
    }

    //Used to inform the server about which video has been selected for labelling
    public static String getVidID(){
        String video = "vid_" + vidID;
        return video;
    }

    //Function to create the JButton containing the thumbnail of the video to be labelled
    private JButton setThumbnail(ArrayList<InitData> initData, int number){
        BufferedImage bImage = null;
        JButton vid;

        ByteArrayInputStream bis = new ByteArrayInputStream(initData.get(number-1).getImageByte());
        try {
            bImage = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon scaledImage = new ImageIcon(bImage.getScaledInstance(400,226,Image.SCALE_DEFAULT));

        vid = new JButton(scaledImage);
        vid.setBorder(BorderFactory.createEmptyBorder());
        vid.setContentAreaFilled(false);

        return vid;
    }

}