package command;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Dao;

public class RegistrationCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		// 업로드 지정 경로
		String appPath = request.getServletContext().getRealPath("");
		// constructs path of the directory to save uploaded file
		String savePath = appPath + File.separator + "profile_img";
		String filename = null;
		String imgName = null;

		System.out.println(savePath);

		int size = 1024 * 1024 * 5;

		String enc = "euc-kr";

		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, size, enc, new DefaultFileRenamePolicy());

			Enumeration enumeration = multi.getFileNames();

			while (enumeration.hasMoreElements()) {

				filename = enumeration.nextElement().toString();
				imgName = multi.getFilesystemName(filename);
				System.out.println("filename : " + imgName);
			}

			String username = multi.getParameter("username");
			String password = multi.getParameter("password");
			String password_confirm = multi.getParameter("password_confirm");

			System.out.println("username : " + username);
			System.out.println("password : " + password);
			System.out.println("password_confirm : " + password_confirm);

			Dao dao = new Dao();
			dao.registration(username, password, imgName);
			System.out.println("registration worked well");
			request.setAttribute("regi_completed", username);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("uploaded");

	}

}
