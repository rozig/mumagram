$(function(){
  var uppy = Uppy.Core({ autoProceed: false })
  uppy.use(Uppy.DragDrop, { target: '#drag-drop-area' })
  uppy.use(Uppy.Tus, { endpoint: 'https://localhost:8080/mumagram/pages/' })
  uppy.run()
});
