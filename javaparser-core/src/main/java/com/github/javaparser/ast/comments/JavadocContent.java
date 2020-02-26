/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2019 The JavaParser Team.
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

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.Generated;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;

import static com.github.javaparser.utils.Utils.EOL;

/**
 * A JavaDoc content node.
 * 
 * @author Simon Sirak
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

    public void addBlockTag(JavadocBlockTag blockTag) {
        if(blockTags == null) {
            blockTags = new NodeList<JavadocBlockTag>();
        }

        blockTags.add(blockTag);
    }

    public NodeList<JavadocBlockTag> getBlockTags() {
        return blockTags;
    }

    /**
     * Return the text content of the document. It does not containing trailing spaces and asterisks
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
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return null;
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {

    }
}
