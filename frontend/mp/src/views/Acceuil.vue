<template>
    <div>
        <div class="row">
            <div class="col-lg-6 mx-auto">
                <blockquote class="blockquote blockquote-custom bg-white p-5 shadow rounded">
                    <div class="blockquote-custom-icon bg-info shadow-sm"><i class="text-white">MP</i></div>
                    <p class="mb-0 mt-2 font-italic">
                        On ne peut pas vous enmener partout...Mais suffisamment loin de chez vous!
                    </p>
                    <footer class="blockquote-footer pt-4 mt-4 border-top">Une personne célèbre
                        <cite title="Source Title">(Mr. Pocket)</cite>
                    </footer>
                </blockquote><!-- END -->
            </div>
        </div>
    </div>
</template>

<script>
export default {
}
</script>


<style scoped>
.blockquote-custom {
  margin-top: 5%;
  position: relative;
  font-size: 1.1rem;
}

.blockquote-custom-icon {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  top: -25px;
  left: 50px;
}
</style>