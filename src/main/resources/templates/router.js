import Dashboard from './spa/Dashboard/Dashboard.vue';
import LogViewer from './spa/LogViewer/LogViewer.vue';
import Keyword from './spa/Keyword/Keyword.vue';

export default[
  {path:'/', component:Dashboard},
  {path:'/logViewer', component:LogViewer},
  {path:'/keyword', component:Keyword}
]