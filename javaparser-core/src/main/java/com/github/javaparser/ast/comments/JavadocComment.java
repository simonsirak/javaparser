/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2020 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */
package com.github.javaparser.ast.comments;

import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.metamodel.JavadocCommentMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.TokenRange;
import java.util.function.Consumer;
import java.util.Optional;
import com.github.javaparser.ast.Generated;
import static com.github.javaparser.StaticJavaParser.parseJavadoc;
import static com.github.javaparser.utils.Utils.EOL;

/**
 * A Javadoc comment. <code>/&#42;&#42; a comment &#42;/</code>
 *
 * @author Julio Vilmar Gesser
 */
public class JavadocComment extends Comment {

    private JavadocDescription description;
    private NodeList<JavadocBlockTag> blockTags;
    
    public JavadocComment() {
        this(null, new JavadocDescription(), new NodeList<JavadocBlockTag>());
    }

    @AllFieldsConstructor
    public JavadocComment(JavadocDescription description, NodeList<JavadocBlockTag> blockTags) {
        this(null, description, blockTags);
    }

    /**
     * This constructor is used by the parser and is considered private.
     */
    @Generated("com.github.javaparser.generator.core.node.MainConstructorGenerator")
    public JavadocComment(TokenRange tokenRange, JavadocDescription description, NodeList<JavadocBlockTag> blockTags) {
        super(tokenRange, "");
        setDescription(description);
        setBlockTags(blockTags);
        customInitialization();
    }

    public JavadocComment setDescription(JavadocDescription description) {
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

    public JavadocComment setBlockTags(NodeList<JavadocBlockTag> blockTags) {
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

    /**
     * Get description of comment
     * 
     * @return description
     */
    public JavadocDescription getDescription() {
        return description;
    }

    /**
     * Get every blog tag of comment
     * 
     * @return all block tags
     */
    public NodeList<JavadocBlockTag> getBlockTags() {
        return blockTags;
    }

    /**
     * Return the text content of the document. It does not contain trailing spaces or asterisks
     * at the start of the line.
     */
    public String toText() {
        // TODO: NOT FINISHED
        StringBuilder sb = new StringBuilder();
        if (!description.toText().isEmpty()) {
            sb.append(description.toText());
            sb.append(EOL);
        }
        if (!blockTags.isEmpty()) {
            sb.append(EOL);
        }
        blockTags.forEach(bt -> {
            sb.append(bt.toText());
            sb.append(EOL);
        });
        return sb.toString();
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.AcceptGenerator")
    public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
        return v.visit(this, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.AcceptGenerator")
    public <A> void accept(final VoidVisitor<A> v, final A arg) {
        v.visit(this, arg);
    }

    public Javadoc parse() {
        return parseJavadoc(getContent());
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.RemoveMethodGenerator")
    public boolean remove(Node node) {
        if (node == null)
            return false;
        return super.remove(node);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.CloneGenerator")
    public JavadocComment clone() {
        return (JavadocComment) accept(new CloneVisitor(), null);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.GetMetaModelGenerator")
    public JavadocCommentMetaModel getMetaModel() {
        return JavaParserMetaModel.javadocCommentMetaModel;
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.ReplaceMethodGenerator")
    public boolean replace(Node node, Node replacementNode) {
        if (node == null)
            return false;
        return super.replace(node, replacementNode);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.TypeCastingGenerator")
    public boolean isJavadocComment() {
        return true;
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.TypeCastingGenerator")
    public JavadocComment asJavadocComment() {
        return this;
    }

    @Generated("com.github.javaparser.generator.core.node.TypeCastingGenerator")
    public void ifJavadocComment(Consumer<JavadocComment> action) {
        action.accept(this);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.node.TypeCastingGenerator")
    public Optional<JavadocComment> toJavadocComment() {
        return Optional.of(this);
    }
}
