/*
 * $Id$
 * $Revision$
 * $Date$
 * $Author$
 *
 * The DOMS project.
 * Copyright (C) 2007-2010  The State and University Library
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package dk.statsbiblioteket.doms.central.connectors.fedora;

import dk.statsbiblioteket.doms.central.connectors.BackendInvalidCredsException;
import dk.statsbiblioteket.doms.central.connectors.BackendInvalidResourceException;
import dk.statsbiblioteket.doms.central.connectors.BackendMethodFailedException;
import dk.statsbiblioteket.doms.central.connectors.fedora.generated.Validation;
import dk.statsbiblioteket.doms.central.connectors.fedora.structures.FedoraRelation;
import dk.statsbiblioteket.doms.central.connectors.fedora.structures.ObjectProfile;
import dk.statsbiblioteket.doms.central.connectors.fedora.structures.SearchResult;

import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA. User: abr Date: Oct 25, 2010 Time: 11:51:52 AM To change this template use File | Settings
 * | File Templates.
 */
public interface Fedora {


    boolean exists(java.lang.String pid,
                   Long asOfDateTime)
            throws
            BackendInvalidCredsException,
            BackendMethodFailedException;

    boolean isDataObject(java.lang.String pid,
                         Long asOfDateTime)
            throws
            BackendInvalidCredsException,
            BackendMethodFailedException;

    boolean isTemplate(java.lang.String pid,
                       Long asOfDateTime)
            throws
            BackendInvalidCredsException,
            BackendMethodFailedException;

    boolean isContentModel(java.lang.String pid,
                           Long asOfDateTime)
            throws
            BackendInvalidCredsException,
            BackendMethodFailedException;


    java.lang.String getObjectXml(String pid,
                                  Long asOfTime)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException;

    java.lang.String ingestDocument(org.w3c.dom.Document document,
                                    java.lang.String pid)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException;


    public ObjectProfile getLimitedObjectProfile(String pid, Long asOfTime) throws
                                                                     BackendMethodFailedException,
                                                                     BackendInvalidCredsException,
                                                                     BackendInvalidResourceException;


    public ObjectProfile getObjectProfile(String pid,
                                          Long asOfTime)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException;

    void modifyObjectState(String pid,
                           String state,
                           String comment)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException;


    Date modifyDatastreamByValue(String pid,
                                 String datastream,
                                 ChecksumType checksumType,
                                 String checksum,
                                 byte[] contents,
                                 List<String> alternativeIdentifiers,
                                 String comment)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException;

    /**
     * Overloaded method for modifying a datastream which throws a ConcurrentModificationException if the
     * datastream has been modified since a given timestamp.
     * @param pid
     * @param datastream
     * @param checksumType
     * @param checksum
     * @param contents
     * @param alternativeIdentifiers
     * @param comment
     * @param lastModifiedDate the timestamp to check, in milliseconds e.g. as generated by Date().getTime().
     * @throws BackendMethodFailedException
     * @throws BackendInvalidCredsException
     * @throws BackendInvalidResourceException
     * @throws ConcurrentModificationException
     */
    Date modifyDatastreamByValue(String pid,
                                 String datastream,
                                 ChecksumType checksumType,
                                 String checksum,
                                 byte[] contents,
                                 List<String> alternativeIdentifiers,
                                 String comment,
                                 Long lastModifiedDate)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException,
            ConcurrentModificationException;

    Date modifyDatastreamByValue(String pid,
                                 String datastream,
                                 ChecksumType checksumType,
                                 String checksum,
                                 byte[] contents,
                                 List<String> alternativeIdentifiers,
                                 String mimeType,
                                 String comment,
                                 Long lastModifiedDate)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException,
            ConcurrentModificationException;

    void deleteObject(String pid, String comment)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException;

    void deleteDatastream(String pid, String datastream, String comment)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException;

    String getXMLDatastreamContents(String pid,
                                    String datastream,
                                    Long asOfDateTime)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException;

    void addRelation(String pid,
                     String subject,
                     String property,
                     String object,
                     boolean literal,
                     String comment)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException;

    void addRelations(String pid,
                     String subject,
                     String property,
                     List<String> objects,
                     boolean literal,
                     String comment)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException;


    List<FedoraRelation> getNamedRelations(String pid,
                                           String name,
                                           Long asOfTime)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException;


    void deleteRelation(String pid,
                        String subject,
                        String predicate,
                        String object,
                        boolean literal,
                        String comment)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException;


    void modifyObjectLabel(String pid,
                           String name,
                           String comment)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException;


    List<SearchResult> fieldsearch(String query,
                                   int offset,
                                   int pageLength)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException;



    Date addExternalDatastream(String pid,
                               String datastream,
                               String label,
                               String url,
                               String formatURI,
                               String mimeType,
                               String checksumType,
                               String checksum,
                               String comment)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException;

    Validation validate(String pid)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException,
            BackendInvalidResourceException;

    public String getUsername();

    public String getPassword();

    String newEmptyObject(String pid,
                          List<String> oldIDs,
                          List<String> collections,
                          String logMessage)
            throws
            BackendMethodFailedException,
            BackendInvalidCredsException;
}
