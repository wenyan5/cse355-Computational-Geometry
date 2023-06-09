import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static JFrame frame = new JFrame("Fisk's Method");
    public static GraphPanel gpanel;
    public static AlgebraPanel algebraPanel;
    public static int[] redgreenblue;
    public static boolean intersecting;
    public  static String erromsg;

    enum PhaseType {DRAW, ANALYZE}
    public static PhaseType phase = PhaseType.DRAW;

    /**
     * Main method of the program
     * @param args args
     */
    public static void main(String[] args)
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout borderLayout = new BorderLayout();
        intersecting = false;
        erromsg = "";
        borderLayout.setHgap(0);
        frame.setLayout(borderLayout);
        frame.setSize(1200,600);
        gpanel = new GraphPanel();
        gpanel.setPreferredSize(new Dimension(700,400));
        frame.add(gpanel, BorderLayout.WEST);
        algebraPanel = new AlgebraPanel();
        algebraPanel.setPreferredSize(new Dimension(500,400));
        frame.add(algebraPanel, BorderLayout.LINE_END);
        InfoPanel infoPanel = new InfoPanel();
        infoPanel.setPreferredSize(new Dimension(1200,100));
        frame.add(infoPanel,BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    /**
     * Activated when the CONTINUE button is pressed, advances to the next Stage.
     */
    public static void phaseAdd()
    {
        phase = PhaseType.values()[(phase.ordinal()+1)%2];
        if (phase == PhaseType.DRAW) {
            intersecting = false;
            erromsg = "";
        }
        if (phase == PhaseType.ANALYZE)
        {
            gpanel.safeIntersect();
        }
        gpanel.repaint();
        algebraPanel.repaint();
    }

    /**
     * Activated when the CLEAR button is pressed, gets rid of the polygon and returns to DRAW Stage.
     */
    public static void phaseClear() {
        gpanel.triangleVertices = new Vertex[6];
        gpanel.repaint();
        phase = PhaseType.DRAW;
        algebraPanel.repaint();
        intersecting = false;
        erromsg = "";
    }
}
