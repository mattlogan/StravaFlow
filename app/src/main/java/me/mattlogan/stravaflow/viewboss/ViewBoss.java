package me.mattlogan.stravaflow.viewboss;

import java.util.Stack;

public class ViewBoss {

    public interface Listener {
        public void showScreen(Screen screen);
    }

    private static ViewBoss singleton;

    private Stack<Screen> layoutStack = new Stack<Screen>();

    private Listener listener;

    private ViewBoss() {
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public static ViewBoss getInstance() {
        if (singleton == null) {
            singleton = new ViewBoss();
        }
        return singleton;
    }

    public void goToScreen(Screen screen) {
        layoutStack.push(screen);
        checkListenerNotNull();
        listener.showScreen(screen);
    }

    public boolean goBack() {
        if (layoutStack.size() <= 1) {
            return false;
        }
        layoutStack.pop();
        checkListenerNotNull();
        listener.showScreen(layoutStack.peek());
        return true;
    }

    public Screen current() {
        if (layoutStack.size() == 0) {
            return null;
        }
        return layoutStack.peek();
    }

    private void checkListenerNotNull() {
        if (listener == null) {
            throw new NullPointerException("ViewBoss Listener may not be null");
        }
    }
}
