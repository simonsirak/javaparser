package com.github.javaparser.ast.comments;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.observer.ObservableProperty;
import static com.github.javaparser.utils.Utils.assertNotNull;

/**
 * Created by simonsirak on 2020-02-25.
 */
public class JavadocDescription extends Node {
    private String description;

    public JavadocDescription() {
        this(null, "");
    }

    public JavadocDescription(TokenRange tokenRange, String content){
        super(tokenRange);
        setDescription(content);
    }

    public JavadocDescription setDescription(final String description) {
        assertNotNull(description);
        if (description == this.description) {
            return (JavadocDescription) this;
        }
        notifyPropertyChange(ObservableProperty.CONTENT, this.description, description);
        this.description = description;
        return this;
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return null;
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {

    }
}
