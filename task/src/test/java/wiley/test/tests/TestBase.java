package wiley.test.tests;

import org.testng.annotations.BeforeTest;
import wiley.test.applicationManager.Application;


public class TestBase {

  public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
  public Application app;

  @BeforeTest
  public void start() {
    if (tlApp.get() != null) {
      app = tlApp.get();
      return;
    }

    app = new Application();
    tlApp.set(app);

    Runtime.getRuntime().addShutdownHook(
            new Thread(() -> { app.quit(); app = null; }));
  }

  public void sleep(int n){
    try {
      Thread.sleep(n);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}