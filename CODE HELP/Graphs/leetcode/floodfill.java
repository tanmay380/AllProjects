class floodfill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        
        int x= image.length;
        int y=image[0].length;
        int oldcolor= image[sr][sc];
        answer(sr,sc,image,newColor,x,y,oldcolor);
        
        return image;
    }
    private void answer(int sx, int sy, int[][] image, int newColor, int x, int y, int oldcolor){
        if(sx<0 || sy<0 || sx>x || sy>y) return; 
        if(image[sx][sy]==newColor) return; 
        image[sx][sy]=newColor;
         answer(sx+1,sy,image,newColor,x,y,oldcolor);
         answer(sx-1,sy,image,newColor,x,y,oldcolor);
         answer(sx,sy+1,image,newColor,x,y,oldcolor);
         answer(sx,sy-1,image,newColor,x,y,oldcolor);      
         System.out.println();
                
    }
    public static void main(String[] args) {
        
    }
}