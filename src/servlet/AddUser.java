package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data_access.UserDAO;
import javabeans.UserDTO;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//■GET通信（ページに直接アクセス）**********************************
		//TOP画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/YourShelf/Index.java");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//■POST通信（ユーザ登録画面から）***********************************
		//◇データ（name,password）があれば登録する

		//パラメータから情報を取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		//データの有無の確認
		if (name != null && name.length() != 0) {//氏名の入力有
			if (password != null && password.length() != 0) {//パスワードの入力有
				//DBにユーザ情報を登録
				UserDAO dao = new UserDAO();
				UserDTO userDTO = dao.addUser(name, password);

				if (userDTO!=null) {//DBへの登録成功
					//セッションスコープに氏名を保存
					HttpSession session = request.getSession();
					session.setAttribute("userDTO",userDTO);//氏名・パスの保存されたビーンズ

					//一般ユーザログイン画面にフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/YourShelf/LoginOK.jsp");
					dispatcher.forward(request, response);

				} else {//DBへの登録失敗

				}
			} else {//パスワード入力なし

			}
		} else {//氏名入力なし

		}
		//TOP画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/YourShelf/Index.java");
		dispatcher.forward(request, response);
	}

}