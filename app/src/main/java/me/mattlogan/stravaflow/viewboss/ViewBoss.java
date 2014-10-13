package me.mattlogan.stravaflow.viewboss;

import android.os.Bundle;

import java.util.Stack;

public class ViewBoss {

    private static final String LAYOUT_STACK_KEY = "layout_stack";

    public interface Listener {
        public void showScreen(Screen screen);
    }

    private Stack<Screen> layoutStack;

    private Listener listener;

    public ViewBoss(Listener listener) {
        this.listener = listener;
    }

    @SuppressWarnings("unchecked")
    public void initializeFromSavedInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(LAYOUT_STACK_KEY)) {
            layoutStack = (Stack<Screen>) savedInstanceState.getSerializable(LAYOUT_STACK_KEY);
        } else {
            layoutStack = new Stack<Screen>();
        }
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

    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(LAYOUT_STACK_KEY, layoutStack);
    }
}
