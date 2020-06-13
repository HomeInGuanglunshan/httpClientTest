package crawl.page.pics;

public class EncodingConvertionTest {

	public static void main(String[] args) throws Exception {
		String s = "¹ú²ú´óÐØÃÃÄÈÂ¶£¬¹úÄ£ÁË²»Æð[24P]_±êÌâ";
		System.out.println(new String(s.getBytes("ISO-8859-1"), "GBK"));
	}
}
