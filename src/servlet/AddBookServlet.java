package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddBookServlet
 */
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBookServlet() {
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

		//■GET通信（管理者メニュー[書籍登録]/直接アクセス）******************************************************************
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String value = request.getParameter("value");

		//アクセス元により分岐------------------------------------------------------------------------
		if (value == null) {//直接アクセスの場合
			//ＴＯＰへリダイレクト
			response.sendRedirect("/YourServlet/Index");

		} else if (value.equals(〇〇)) {//管理者メニュー[書籍登録]からアクセスの場合
			//書籍登録画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/YourShelf/addBook.jsp");
			dispatcher.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		//■POST通信（直接アクセス/管理者[検索ボタン/登録ボタン]からアクセス）********************************
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String value = request.getParameter("value");

		//アクセス元により分岐------------------------------------------------------------------------
		if (value == null) {//直接アクセスの場合
			//ＴＯＰへリダイレクト
			response.sendRedirect("/YourServlet/Index");

		} else if (value.equals(〇〇)) {//検索ボタンからアクセスの場合
			String isbn = request.getParameter("〇〇");//ＩＳＢＮデータ取得
			String title = request.getParameter("〇〇");//本のタイトル取得
			//★書籍データをＡＰＩを使用して取得
			//※書籍情報を「book」変数に格納

			//★検索書籍データの有無をDBで照合⇒合致したデータをlistsで返す

			if (result) {//書籍データあった場合
				//書籍一覧情報（lists）を取得し、セッションスコープに保存
				List<LendingBook> lists = dao.searchBook();
				HttpSession session = request.getSession();
				session.setAttribute("〇〇", 〇〇);

				//検索結果一覧表示画面にフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/YourShelf/searchApiResult.jsp");
				dispatcher.forward(request, response);
			} else {//書籍データない場合
					//エラーメッセージを保存
					//String errorMsg="検索された書籍はありません";
					//エラーメッセージをセッションスコープに保存
					//HttpSession session=request.getSession();
					//session.setAttribute("errorMsg",errorMsg);

			}

		} else if (value.equals(〇〇)) {//登録ボタンからアクセスの場合
			String isbn = request.getParameter("〇〇");//ＩＳＢＮデータ取得
			String title = request.getParameter("〇〇");//本のタイトル取得
			//★書籍データをＡＰＩを使用して取得
			//※書籍情報を「book」変数に格納

			//★取得した書籍データをDBへ保存⇒結果をbooleanで返す
			HavingBookDAO dao = new HavingBookDAO();
			boolean result = dao.addBook(book);

			if (result) {//DBへの保存成功
				//検索書籍一覧情報を取得し、セッションスコープに保存
				List<LendingBook> lists = dao.searchBook();
				HttpSession session = request.getSession();
				session.setAttribute("lists", lists);

				//登録OK表示画面にフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/YourShelf/addBookOK.jsp");
				dispatcher.forward(request, response);
			} else {//DBへの保存失敗
					//エラーメッセージを保存
					//String errorMsg="登録に失敗しました";
					//エラーメッセージをセッションスコープに保存
					//HttpSession session=request.getSession();
					//session.setAttribute("errorMsg",errorMsg);
			}

		}

	}
}