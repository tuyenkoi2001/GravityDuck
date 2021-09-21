import java.io.*;

public class Map {
    int[][] map = new int[15][20];
    String filePath = new File("").getAbsolutePath();
    String[] line = new String[15];
    String DuckPos;
    String EggPos;
    String SwitchPos;
    String BlockPos;

    public Map(int level){
        filePath += "\\src\\Maps\\" + level+".txt";
        System.out.println(filePath);

        // đọc bản đồ map từ file
        try {
            FileInputStream fis = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bfr = new BufferedReader(isr);
            for(int i=0;i<15;i++){
                line[i] = bfr.readLine();
            }
            DuckPos = bfr.readLine();
            EggPos = bfr.readLine();
            SwitchPos = bfr.readLine();
            BlockPos = bfr.readLine();

            bfr = null;
            isr.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] row;
        for(int i=0;i<15;i++){
            row = line[i].split(" ");
            for(int j=0;j<20;j++)
                map[i][j] = Integer.parseInt(row[j]);
        }

        row = null;
        line = null;
        filePath = null;
    };

    // lấy vị trí của vịt đã được lưu sẵn ở file
    public int[] getDuckPos(){
        int[] x = new int[2];
        String[] a = DuckPos.split(" ");
        x[0] = Integer.parseInt(a[0]);
        x[1] = Integer.parseInt(a[1]);
        return x;
    }

    public int[] getEggPos(){
        int[] x = new int[2];
        String[] a = EggPos.split(" ");
        x[0] = Integer.parseInt(a[0]);
        x[1] = Integer.parseInt(a[1]);
        return x;
    }

    public int[] getBlockPos(){
        int[] blockpos = null;
        if(this.BlockPos != null){
            String[] line;
            line = BlockPos.split(" ");
            int size = line.length;
            blockpos = new int[size];
            for(int i=0;i<size;i++){
                blockpos[i] = Integer.parseInt(line[i]);
            }
        }
        return blockpos;
    }

    public boolean hasABlock(){
        if(BlockPos!=null)
            return true;
        return false;
    }

    public boolean hasASwitch(){
        if(SwitchPos!=null && !this.SwitchPos.equals("")) return true;
        return false;
    }

    public int[] getSwitchPos(){
        int[] switchPos = null;
        if(this.SwitchPos != null && !this.SwitchPos.equals("")){
            String[] line;
            line = SwitchPos.split(" ");
            int size = line.length;
            switchPos = new int[size];
            for(int i=0;i<size;i++){
                switchPos[i] = Integer.parseInt(line[i]);
            }
        }
        return switchPos;
    }

    public int[][] getMap(){
        return this.map;
    }

}

