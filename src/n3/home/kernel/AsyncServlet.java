package n3.home.kernel;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import n3.home.base.BaseServlet;


//@WebServlet(name="AsyncServlet",urlPatterns={"/asyncServlet"},asyncSupported=true)
public class AsyncServlet extends BaseServlet {

	private static final long serialVersionUID = 5848259560527332306L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("start doGet");
//		PrintWriter out;
//					out = resp.getWriter();
//					out.println("prepare AsyncServlet");
//					out.flush();
		try {
			Thread.sleep(7000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
					AsyncContext asyncContext = req.startAsync();
					new AsyncServletThread(asyncContext).start();
//					out.println("execute AsyncServlet");
//					out.flush();
//		out.close();
		System.out.println("end doGet");
	}
	
	class AsyncServletThread extends Thread {

		public AsyncServletThread(AsyncContext asyncContext) {
			this.asyncContext = asyncContext;
		}
		
		private AsyncContext asyncContext;
		
		@Override
		public void run() {
			try {
				System.out.println("start asyncContext");
				Thread.sleep(3000L);
//				PrintWriter out = asyncContext.getResponse().getWriter();
//				out.println("AsyncServletThread");
				try {
					asyncContext.getRequest().getRequestDispatcher("/login.jsp")
					.forward(asyncContext.getRequest(), asyncContext.getResponse());
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				asyncContext.complete();
				System.out.println("done asyncContext");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
