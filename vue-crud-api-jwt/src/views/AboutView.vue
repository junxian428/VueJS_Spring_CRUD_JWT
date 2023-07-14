<template>
  <div class="about">
    <h3>PLC Monitoring System</h3>
    <img :src="imageUrl" alt="Image" style="width: 500px; height: 300px;">

  </div>
</template>



<script>
export default {
  data() {
    return {
      imageUrl: './IoTMonitoringSystem.png'  // Replace with the URL of your image
    };
  }
};
</script>