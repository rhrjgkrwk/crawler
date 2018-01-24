import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.opencsv.CSVWriter; //csv말고 xls로 저장하도록 다른 라이브러리를 쓰자.

public class Crawler {

	public Crawler(String pathDir) {
		// TODO Auto-generated constructor stub
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yymmdd_hhmm");
		String str = dayTime.format(new Date(time));
		
		Elements contents[] = new Elements[5];
		String chart[] = new String[100];
		
		String path = pathDir + "\\" + str + "_Charts.csv";
		File file = new File(path);
		try {
			
			Document doc = Jsoup.connect("https://itopchart.com/us/en/top-songs/k-pop/").get();
			
			contents[0] = doc.select(".rank"); // rank
			contents[1] = doc.select(".artist"); // artist
			contents[2] = doc.select("h3.app-name > a"); // name
			for (int i = 0; i < contents.length; i++) {
				chart[i] = contents[0].get(i).text()+"\t"+contents[1].get(i).text()+"\t"+contents[2].get(i).text();
			}

//			System.out.println(contents[0].get(0).text()); //
//			System.out.println(contents[1].get(0).text()); //
//			System.out.println(contents[2].get(0).text()); //

		} catch (IOException e1) { // Jsoup의 connect 부분에서 IOException 오류가 날 수 있으므로 사용한다.

			e1.printStackTrace();

		}
		
		try {
			CSVWriter w = new CSVWriter(new FileWriter(file));
			for (int i = 0; i < contents.length; i++) {
				w.writeNext(chart);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// 여기에 action 정의
	// 여기서 크롤링이랑 엑셀 저장까지?????
	// for를 돌려서 각 국의 차트를 긁어서 저장하자. 41개국.. 일본/중국/영국/칠레/싱가폴/미국 순

}

//
//
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.Iterator;
// import java.util.List;
//
// public class CSVWrite {
//
// private String filename = "mycsv.csv";
//
// public CSVWrite() {}
//
// public void writeCsv(List<String[]> data) {
// try {
// CSVWriter cw = new CSVWriter(new FileWriter(filename), ',', '"');
// Iterator<String[]> it = data.iterator();
// try {
// while (it.hasNext()) {
// String[] s = (String[]) it.next();
// cw.writeNext(s);
// }
// } finally {
// cw.close();
// }
// } catch (IOException e) {
// e.printStackTrace();
// }
// }
// }