from pymongo import MongoClient
from bson.objectid import ObjectId

class AnimalShelter(object):
    """CRUD operations for Animal collection in MongoDB"""

    def __init__(self, username, password, host='nv-desktop-services.apporto.com', port=30043, db='AAC', collection='animals'):
        
        # Initialize Connection
        self.client = MongoClient(f'mongodb://{username}:{password}@{host}:{port}/')
        self.database = self.client[db]
        self.collection = self.database[collection]

    def create(self, data):
        """Inserts a document into the MongoDB collection."""
        if data is not None:
            try:
                result = self.collection.insert_one(data) #Insert the document
                return True # If insertion was successful
            except Exception as e:
                print(f"An error occurred: {e}")
                return False # If unsuccessful
        else:
            raise Exception("Nothing to save, because data parameter is empty")

    def read(self, criteria):
        """Queries for documents and returns them as a list from MongoDB."""
        try:
            documents = list(self.collection.find(criteria))
            return documents  # Return the list of documents
        except Exception as e:
            print(f"An error occurred: {e}")
            return [] # Return an empty list if an error occurs
        
    def update(self, criteria, update_values):
        """Updates documents matching criteria with new values."""
        try:
            result = self.collection.update_many(criteria, update_values)
            return result.modified_count # Return the number of documents modified
        except Exception as e:
            print(f"An error occurred during update: {e}")
            return 0 # Return 0 if no documents were updated

    def delete(self, criteria):
        """Deletes documents matching criteria."""
        try:
            result = self.collection.delete_many(criteria)
            return result.deleted_count # Return the number of documents deleted
        except Exception as e:
            print(f"An error occurred during delete: {e}")
            return 0 # Return 0 if no documents were deleted
