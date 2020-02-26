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
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.observer.ObservableProperty;
import static com.github.javaparser.utils.Utils.assertNotNull;

/**
 * A JavaDoc description.
 * 
 * @author Simon Sirak & David Johansson
 */
public class JavadocDescription extends Node {
    private String description; 
    private NodeList<JavadocBlockTag> inlineTags;   

    public JavadocDescription() {
        this(null, "", new NodeList<JavadocBlockTag>());
    }

    public JavadocDescription(TokenRange tokenRange, String content, NodeList<JavadocBlockTag> inlineTags){
        super(tokenRange);
        setDescription(content);
        setInlineTags(inlineTags);
    }

    /**
     * Set description of current node.
     * 
     * @param description string description 
     * @return new version of current object
     */
    public JavadocDescription setDescription(final String description) {
        assertNotNull(description);
        if (description == this.description) {
            return (JavadocDescription) this;
        }
        notifyPropertyChange(ObservableProperty.CONTENT, this.description, description);
        this.description = description;
        return this;
    }

    /**
     * Set inline tags of current description.
     * 
     * @param inlineTags list containing all inline tags 
     * @return new version of current object
     */
    public JavadocDescription setInlineTags(NodeList<JavadocBlockTag> inlineTags) {
        if (inlineTags == this.inlineTags) {
            return this;
        }        
        notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.inlineTags, inlineTags);
        if (this.inlineTags != null)
            this.inlineTags.setParentNode(null);
        this.inlineTags = inlineTags;
        setAsParentNodeOf(inlineTags);
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
