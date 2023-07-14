<template>
    <div>
      <h3>Alarm Configuration</h3>
  
      <!-- Form for creating new item -->
      <form @submit.prevent="addItem">
        <input v-model="newItemText" type="text" placeholder="Enter item text" required>
        <button type="submit">Add Item</button>
      </form>
  
      <!-- List of items -->
      <ul>
        <li v-for="(item, index) in items" :key="item.id">
          <span v-if="index !== editIndex">
            {{ item.text }}
            <button @click="editItem(index)">Edit</button>
          </span>
          <span v-else>
            <form @submit.prevent="updateItem">
              <input v-model="editedItemText" type="text" required>
              <button type="submit">Update</button>
              <button @click="cancelEdit">Cancel</button>
            </form>
          </span>
          <button @click="deleteItem(item.id)">Delete</button>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        items: [],                // Array to store items
        newItemText: '',          // Text for new item
        editIndex: null,          // Index of item being edited (-1 for new item)
        editedItemText: '',       // Text for the item being edited
      };
    },
    mounted() {
      this.fetchItems();
    },
    methods: {
      fetchItems() {


        const sessionCookie = document.cookie.replace(
          /(?:(?:^|.*;\s*)access_Token\s*\=\s*([^;]*).*$)|^.*$/,
          '$1'
        );
        const blobCookie = document.cookie.replace(
            /(?:(?:^|.*;\s*)blob_data\s*\=\s*([^;]*).*$)|^.*$/,
            '$1'
          );

        
        axios.get('http://localhost:8082/api/items', {
          headers: {
            Authorization: `Bearer ${sessionCookie}`,
          },
        })
          .then(response => {
            this.items = response.data;
          })
          .catch(error => {
            console.error('Error fetching items:', error);
          });
      },
      addItem() {

        const sessionCookie = document.cookie.replace(
          /(?:(?:^|.*;\s*)access_Token\s*\=\s*([^;]*).*$)|^.*$/,
          '$1'
        );

        const blobCookie = document.cookie.replace(
            /(?:(?:^|.*;\s*)blob_data\s*\=\s*([^;]*).*$)|^.*$/,
            '$1'
          );

        const newItem = {
          text: this.newItemText,
        };
        console.log(newItem);
        axios.post('http://localhost:8082/api/items', newItem, {
          headers: {
            Authorization: `Bearer ${sessionCookie}`,

          },
        })
          .then(response => {
            this.items.push(response.data);
            this.newItemText = '';
          })
          .catch(error => {
            console.error('Error adding item:', error);
          });
      },
      editItem(index) {
        this.editIndex = index;
        this.editedItemText = this.items[index].text;
      },
      updateItem() {

        const sessionCookie = document.cookie.replace(
          /(?:(?:^|.*;\s*)access_Token\s*\=\s*([^;]*).*$)|^.*$/,
          '$1'
        );

        const blobCookie = document.cookie.replace(
            /(?:(?:^|.*;\s*)blob_data\s*\=\s*([^;]*).*$)|^.*$/,
            '$1'
          ); 
        const updatedItem = {
          id: this.items[this.editIndex].id,
          text: this.editedItemText,
        };
  
        axios.put(`http://localhost:8082/api/items/${updatedItem.id}`, updatedItem, {
          headers: {
            Authorization: `Bearer ${sessionCookie}`,

          },
        })
          .then(() => {
            this.items[this.editIndex].text = this.editedItemText;
            this.cancelEdit();
          })
          .catch(error => {
            console.error('Error updating item:', error);
          });
      },
      cancelEdit() {
        this.editIndex = null;
        this.editedItemText = '';
      },
      deleteItem(itemId) {
        const sessionCookie = document.cookie.replace(
          /(?:(?:^|.*;\s*)access_Token\s*\=\s*([^;]*).*$)|^.*$/,
          '$1'
        );

        const blobCookie = document.cookie.replace(
            /(?:(?:^|.*;\s*)blob_data\s*\=\s*([^;]*).*$)|^.*$/,
            '$1'
          );

        axios.delete(`http://localhost:8082/api/items/${itemId}`, {
          headers: {
            Authorization: `Bearer ${sessionCookie}`,
          },
        })
          .then(() => {
            this.items = this.items.filter(item => item.id !== itemId);
          })
          .catch(error => {
            console.error('Error deleting item:', error);
          });
      },
    },
  };
  </script>
  