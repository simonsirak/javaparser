package com.github.javaparser.ast.comments;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.Generated;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;

/**
 * Created by simonsirak on 2020-02-25.
 */
public class JavadocContent extends Node {

    private JavadocDescription description;
    private NodeList<JavadocBlockTag> blockTags;

    public JavadocContent() {
        this(null, new JavadocDescription(), new NodeList<JavadocBlockTag>());
    }

    /**
     * This constructor is used by the parser and is considered private.
     */
    @Generated("com.github.javaparser.generator.core.node.MainConstructorGenerator")
    public JavadocContent(TokenRange tokenRange, JavadocDescription description, NodeList<JavadocBlockTag> blockTags) {
        super(tokenRange);
        setDescription(description);
        setBlockTags(blockTags);
        customInitialization();
    }

    protected JavadocContent(TokenRange tokenRange) {
        super(tokenRange);
    }

    public JavadocContent setDescription(JavadocDescription description) {
        // MAYBE we should not say that null == empty
        //if (description == null) {
        //    description = new JavadocDescription();
        //}
        if (description == this.description) {
            return this;
        }
        // TODO: Add observable property for JavadocDescription and the other tags.
        notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.description, description);
        if (this.description != null)
            this.description.setParentNode(null);
        this.description = description;
        setAsParentNodeOf(description);
        return this;
    }

    public JavadocContent setBlockTags(NodeList<JavadocBlockTag> blockTags) {
        // MAYBE we should not say that null == empty
        //if (description == null) {
        //    description = new JavadocDescription();
        //}
        if (blockTags == this.blockTags) {
            return this;
        }
        // TODO: Add observable property for JavadocDescription and the other tags.
        notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.blockTags, blockTags);
        if (this.blockTags != null)
            this.blockTags.setParentNode(null);
        this.blockTags = blockTags;
        setAsParentNodeOf(blockTags);
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
