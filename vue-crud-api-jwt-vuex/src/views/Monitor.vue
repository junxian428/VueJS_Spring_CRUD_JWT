<template>
    <div class="image-container" style="display:inline;">
      <div class="image-wrapper">
        <img v-if="showImage" src="WaterTank.jpg" alt="Image" style="width: 300px; height: 400px;"/>
        <div class="label">Nitrogen Tank</div>
        <button @click="toggleImage" class="toggle-button">Toggle Image</button>
      </div>
    </div>

    <div class="image-container" style="display:inline;">
      <div class="image-wrapper">
        <img v-if="showImage" src="Pipe.png" alt="Image" style="width: 150px; height: 400px;"/>
        <div class="label">Pipe</div>
        <button @click="toggleImage" class="toggle-button">Toggle Image</button>
      </div>
    </div>

    <div class="image-container" style="display:inline;">
      <div class="image-wrapper">
        <img v-if="showImage" src="WaterTank.jpg" alt="Image" style="width: 300px; height: 400px;"/>
        <div class="label">Nitrogen Tank</div>
        <button @click="toggleImage" class="toggle-button">Toggle Image</button>
      </div>
    </div>

    <div class="image-container" style="display:inline;">
      <div class="image-wrapper">
        <img v-if="showImage" src="Pipe.png" alt="Image" style="width: 150px; height: 400px;"/>
        <div class="label">Pipe</div>
        <button @click="toggleImage" class="toggle-button">Toggle Image</button>
      </div>
    </div>


    <div class="image-container" style="display:inline;">
      <div class="image-wrapper">
        <img v-if="showImage" src="WaterTank.jpg" alt="Image" style="width: 300px; height: 400px;"/>
        <div class="label">Nitrogen Tank</div>
        <button @click="toggleImage" class="toggle-button">Toggle Image</button>
      </div>
    </div>

    <div class="image-container" style="display:inline;">
      <div class="image-wrapper">
        <img v-if="showImage" src="WaterTank.jpg" alt="Image" style="width: 300px; height: 400px;"/>
        <div class="label">Nitrogen Tank</div>
        <button @click="toggleImage" class="toggle-button">Toggle Image</button>
      </div>
    </div>


    <div class="image-container">
      <div class="image-wrapper">
        <img v-if="showImage" src="Pipe_V.png" alt="Image" style="width: 300px; height: 150px;"/>
        <div class="label">Pipe</div>
        <button @click="toggleImage" class="toggle-button">Toggle Image</button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        showImage: true
      };
    },
    methods: {
      toggleImage() {
        //this.showImage = !this.showImage;
        console.log("on");
      }
    }
  };
  </script>
  
  <style scoped>
  .image-container {
    position: relative;
  }
  
  .image-wrapper {
    position: relative;
    display: inline-block;
  }
  
  .toggle-button {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 50%;
}

  </style>
  