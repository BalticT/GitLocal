package myweb;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUpload
 */
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		final PrintWriter writer = response.getWriter();
		First fs = new First();

		
		
		try {

			ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
			List<FileItem> multifiles = sf.parseRequest(request);

			for (FileItem item : multifiles) {

				item.write(new File("C:/temp/" + item.getName()));

				System.out.println("C:/temp/" + item.getName());

				System.out.println("File Uploaded : " + item.getName());

				writer.println("New file " + item.getName() + " created at C:/temp/ ");
				writer.print("<br>");

			}
		} catch (Exception e) {
			System.out.println(e);
		}


		fs.run();

		// fs.start();
		// writer.println(First.kiekiai.toString() );
 
		Iterator<Entry<String, Integer>> it = First.kiekiai.entrySet().stream().iterator();

		while (it.hasNext()) {

			Map.Entry<String, Integer> pairs = it.next();

			writer.print(pairs.getKey() + " " + pairs.getValue() + "<br>" + "\n");
		}


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		response.getWriter().append("Served at: ").append(request.getContextPath());

		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);

	}

}
