import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

//import com.opencsv.CSVWriter; //csv말고 xls로 저장하도록 다른 라이브러리를 쓰자.

public class Crawler {

	public Crawler() {
		// TODO Auto-generated constructor stub
	}

	// 여기에 action 정의
	// 여기서 크롤링이랑 엑셀 저장까지?????
	// for를 돌려서 각 국의 차트를 긁어서 저장하자. 41개국.. 일본/중국/영국/칠레/싱가폴/미국 순

	public Elements[] crawlChart(String nation) {
		Elements contents[] = new Elements[3];// rank, artist, name
		// String chart[] = new String[100];
		try {

			Document doc = Jsoup.connect("https://itopchart.com/" + nation + "/en/top-songs/k-pop/").get();

			contents[0] = doc.select(".rank"); // rank
			contents[1] = doc.select(".artist"); // artist
			contents[2] = doc.select("h3.app-name > a"); // name

			System.out.println(contents[0].get(0).text()); //
			System.out.println(contents[1].get(0).text()); //
			System.out.println(contents[2].get(0).text()); //

		} catch (IOException e1) { // Jsoup의 connect 부분에서 IOException 오류가 날 수
									// 있으므로 사용한다.
			e1.printStackTrace();
		}
		return contents;
	}

	public File getFilePath(String pathDir) { // 경로를 받아오자.
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yymmdd_hhmm");
		String str = dayTime.format(new Date(time));

		String temp = pathDir + "\\" + str + "_Charts.xls";
		File path = new File(temp);
		return path;
	}

	public void makeExcel(HashMap<String,String> nation, String pathDir, Elements[] contents) { // 여기서는 file이 경로,// contents는 크롤링 데이터
		// excel로 저장
		File path = getFilePath(pathDir); //path를 받아오자
		
		//받아온 해쉬맵을 iter돌려서 시트를 하나씩 맹글자.
		
		
		HSSFWorkbook workbook = new HSSFWorkbook(); // 새 엑셀 생성
		/*for (Iterator iterator = collection.iterator(); iterator.hasNext();) { 여기는 hashmap돌리는 곳.
			type type = (type) iterator.next();
			
		}*/ 
		Iterator<String> nations = nation.keySet().iterator();
        while( nations.hasNext() ){
            String nationName = nations.next();
            String nationCode = nation.get(nationName);            
            Elements[] data = crawlChart(nationCode);
            
            HSSFSheet sheet = workbook.createSheet("시트명"); // 새 시트(Sheet) 생성
    		HSSFRow row = sheet.createRow(0); // 엑셀의 행은 0번부터 시작
    		HSSFCell cell = row.createCell(0); // 행의 셀은 0번부터 시작
    		
    		cell.setCellValue("0월 0주차 "+nationName+" Chart"); // title을 집어넣자.
    		
    		for (int i = 0; i < 3; i++) { //row
				
			}
    		
    		try {
    			FileOutputStream fileoutputstream = new FileOutputStream(path);
    			workbook.write(fileoutputstream);
    			fileoutputstream.close();
    			System.out.println("엑셀파일생성성공");
    		} catch (IOException e) {
    			e.printStackTrace();
    			System.out.println("엑셀파일생성실패");
    		}   
        }
	}
}