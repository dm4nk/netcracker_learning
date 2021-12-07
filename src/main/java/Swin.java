import model.buildings.Building;
import model.buildings.Floor;
import model.buildings.Space;
import model.exeptions.FloorIndexOutOfBoundsException;
import model.utilities.Buildings;
import model.utilities.factories.BuildingFactory;
import model.utilities.factories.impl.DwellingFactory;
import model.utilities.factories.impl.HotelFactory;
import model.utilities.factories.impl.OfficeFactory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Swin {

    //Strings
    private static final String SWING_VOPROS = "Swing???";
    private static final JFrame mainFrame = getJFrame();
    //For Panel1
    private static final String TYPE_STR = "Type ";
    private static final String BUILDING_STR = "Building ";
    //For Panel2
    private static final String FLOOR_STR = "Floor";
    //For Panel3
    private static final String SPACE_3_STR = "Space";
    //Common
    private static final String SIZE_STR = "Size ";
    private static final String HASH_TAG_STR = "# ";
    private static final String SQUARE_STR = "Square ";
    //Panels
    private static final JPanel rootPanel = new JPanel();
    private static final JPanel jPanel1 = new JPanel();
    private static final JPanel jPanel2 = new JPanel();
    private static final JPanel jPanel3 = new JPanel();
    private static final JPanel jPanel4 = new JPanel();
    //For Panel1
    private static final Label BUILDING_LABEL_1 = new Label(BUILDING_STR, Label.CENTER);

    //Labels
    private static final Label BUILDING_TYPE_1 = new Label(TYPE_STR, Label.CENTER);
    private static final Label BUILDING_FLOORS_1 = new Label(SIZE_STR, Label.CENTER);
    private static final Label BUILDING_SQUARE_1 = new Label(SQUARE_STR, Label.CENTER);
    //For Panel2
    private static final Label FLOOR_2 = new Label(FLOOR_STR, Label.CENTER);
    private static final Label HASHTAG_2 = new Label(HASH_TAG_STR, Label.CENTER);
    private static final Label SIZE_2 = new Label(SIZE_STR, Label.CENTER);
    private static final Label SQUARE_2 = new Label(SQUARE_STR, Label.CENTER);
    //For Panel3
    private static final Label SPACE_3 = new Label(SPACE_3_STR, Label.CENTER);
    private static final Label HASHTAG_3 = new Label(HASH_TAG_STR, Label.CENTER);
    private static final Label SIZE_3 = new Label(SIZE_STR, Label.CENTER);
    private static final Label SQUARE_3 = new Label(SQUARE_STR, Label.CENTER);
    //Common
    private static final String DEFAULT_DIRECTORY = "C:/Users/Dmitrii/Desktop";
    private static Building building;


    private Swin() {
    }

    public static void main(String[] args) {
        mainFrame.add(rootPanel);
        mainFrame.setResizable(false);
        setPanelSettings();
        setRootPanelSettings();

        mainFrame.setJMenuBar(getMenuBar());
        mainFrame.revalidate();
    }

    private static void setRootPanelSettings() {
        rootPanel.add(jPanel1);
        rootPanel.add(jPanel2);
        rootPanel.add(jPanel3);
        rootPanel.add(jPanel4);
    }


    private static JFrame getJFrame() {
        JFrame jFrame = new JFrame() {
        };
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = 800;
        int height = 600;
        jFrame.setBounds(dimension.width / 2 - width / 2, dimension.height / 2 - height / 2, width, height);

        jFrame.setTitle(SWING_VOPROS);

        return jFrame;
    }

    private static void updatePlanPanel() {
        rootPanel.removeAll();
        jPanel4.removeAll();

        for (int i = 0; i < building.size(); ++i) {
            JPanel floorPanel = new JPanel();
            floorPanel.setBorder(BorderFactory.createEtchedBorder());
            floorPanel.add(new Label(Integer.toString(i + 1)));
            int finalI1 = i;
            floorPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    updatePanel2(building.getFloor(finalI1), finalI1);
                }
            });
            for (int j = 0; j < building.getFloor(i).size(); ++j) {
                JButton jButton = new JButton(Integer.toString(j + 1));
                int finalI = i;
                int finalJ = j;
                jButton.addActionListener(e -> updatePanel3(building.getFloor(finalI).getSpace(finalJ), finalJ));
                floorPanel.add(jButton);
            }
            jPanel4.add(floorPanel);
        }
        JScrollPane scrollPane = new JScrollPane(jPanel4, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        updatePanel1();
        rootPanel.add(scrollPane);
        rootPanel.add(jPanel1);
        rootPanel.add(jPanel2);
        rootPanel.add(jPanel3);
        mainFrame.revalidate();
    }


    private static void updatePanel1() {
        BUILDING_TYPE_1.setText(TYPE_STR + building.getClass().getSimpleName());
        BUILDING_FLOORS_1.setText(SIZE_STR + building.size());
        BUILDING_SQUARE_1.setText(SQUARE_STR + building.size());

        jPanel1.repaint();
        jPanel1.revalidate();
    }

    private static void updatePanel2(Floor floor, int num) {
        HASHTAG_2.setText(HASH_TAG_STR + (num + 1));
        SIZE_2.setText(SIZE_STR + floor.size());
        SQUARE_2.setText(SQUARE_STR + floor.sumSquare());

        jPanel2.repaint();
        jPanel2.revalidate();
    }

    private static void updatePanel3(Space space, int num) {
        HASHTAG_3.setText(HASH_TAG_STR + (num + 1));
        SIZE_3.setText(SIZE_STR + space.getRooms());
        SQUARE_3.setText(SQUARE_STR + space.getSpace());

        jPanel3.repaint();
        jPanel3.revalidate();
    }

    private static void setPanelSettings() {

        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));
        jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.Y_AXIS));
        jPanel4.setLayout(new BoxLayout(jPanel4, BoxLayout.Y_AXIS));

        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel2.setBorder(BorderFactory.createEtchedBorder());
        jPanel3.setBorder(BorderFactory.createEtchedBorder());
        jPanel4.setBorder(BorderFactory.createEtchedBorder());

        //1 Panel
        jPanel1.add(BUILDING_LABEL_1);
        jPanel1.add(BUILDING_TYPE_1);
        jPanel1.add(BUILDING_FLOORS_1);
        jPanel1.add(BUILDING_SQUARE_1);

        //2 Panel
        jPanel2.add(FLOOR_2);
        jPanel2.add(HASHTAG_2);
        jPanel2.add(SIZE_2);
        jPanel2.add(SQUARE_2);

        //3 Panel
        jPanel3.add(SPACE_3);
        jPanel3.add(HASHTAG_3);
        jPanel3.add(SIZE_3);
        jPanel3.add(SQUARE_3);

        jPanel4.add(BUILDING_LABEL_1);
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.X_AXIS));
    }

    private static JMenuBar getMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu lookAndFeel = new JMenu("Look&Feel");
        jMenuBar.add(file);
        jMenuBar.add(lookAndFeel);

        JMenuItem dwelling = new JMenuItem("Open Dwelling");
        JMenuItem office = new JMenuItem("Open Office Building");
        JMenuItem hotel = new JMenuItem("Open Hotel");

        ButtonGroup group = new ButtonGroup();

        for (UIManager.LookAndFeelInfo lookAndFeelInfo : UIManager.getInstalledLookAndFeels()) {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(lookAndFeelInfo.getName());

            item.addActionListener(click -> {
                try {
                    UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                    rootPanel.repaint();
                    updatePlanPanel();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
            });
            group.add(item);
            lookAndFeel.add(item);
        }

        file.add(dwelling).addActionListener(new BuildingOpener(new DwellingFactory()));
        file.addSeparator();
        file.add(office).addActionListener(new BuildingOpener(new OfficeFactory()));
        file.addSeparator();
        file.add(hotel).addActionListener(new BuildingOpener(new HotelFactory()));
        file.addSeparator();
        file.add(new JMenuItem("Exit")).addActionListener(e -> System.exit(0));

        return jMenuBar;
    }

    private static class BuildingOpener extends AbstractAction {

        private final BuildingFactory factory;

        BuildingOpener(BuildingFactory factory) {
            this.factory = factory;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setCurrentDirectory(new File(DEFAULT_DIRECTORY));
            jFileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.getName().endsWith(".txt");
                }

                @Override
                public String getDescription() {
                    return "TXT files only";
                }
            });
            jFileChooser.showOpenDialog(mainFrame);
            File file = jFileChooser.getSelectedFile();
            Buildings.setBuildingFactory(factory);
            FileReader fr = null;
            try {
                fr = new FileReader(file);
            } catch (IOException | NumberFormatException | FloorIndexOutOfBoundsException fileNotFoundException) {
                JOptionPane.showMessageDialog(rootPanel,
                        "Can't read building from that file",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                fileNotFoundException.printStackTrace();
            }
            try {
                building = Buildings.readBuilding(fr);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            if (building == null) {
                JOptionPane.showMessageDialog(rootPanel,
                        "Can't read building from that file",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            updatePlanPanel();
            updatePanel1();
        }
    }
}
